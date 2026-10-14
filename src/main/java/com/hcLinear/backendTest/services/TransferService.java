package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.CreateTransferDTO;
import com.hcLinear.backendTest.dto.TransferDTO;
import com.hcLinear.backendTest.dto.TransferMapper;
import com.hcLinear.backendTest.entities.*;
import com.hcLinear.backendTest.exceptions.ConflictException;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.PlayerRepository;
import com.hcLinear.backendTest.repositories.TransferRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private final TransferRequestRepository transferRequestRepository;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final TransferMapper transferMapper;
    private final TransferDocumentService transferDocumentService;
    private static final int MAX_TEAM_SIZE = 25;

    public TransferService(TransferRequestRepository transferRequestRepository, PlayerService playerService, PlayerRepository playerRepository, TeamService teamService, TransferMapper transferMapper, TransferDocumentService transferDocumentService) {
        this.transferRequestRepository = transferRequestRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.transferMapper = transferMapper;
        this.transferDocumentService = transferDocumentService;
    }

    public TransferDTO createTransfer(CreateTransferDTO transferRequestDTO) {
        PlayerEntity player = playerService.findEntityById(transferRequestDTO.playerId());
        TeamEntity targetTeam = teamService.findEntityById(transferRequestDTO.targetTeamId());
        TeamEntity sourceTeam = player.getTeam();

        if(sourceTeam != null && sourceTeam.getId().equals(targetTeam.getId())){
            throw new ValidationException("A forrás- és célcsapat nem egyezhet meg!");
        }
        if(transferRequestDTO.expiresAt().isBefore(LocalDate.now())){
            throw new ValidationException("A lejárati dátum nem lehet a mai napnál korábbi!");
        }

        TransferRequestEntity transfer = new TransferRequestEntity();
        transfer.setPlayer(player);
        transfer.setTargetTeam(targetTeam);
        transfer.setSourceTeam(sourceTeam);
        transfer.setExpiresAt(transferRequestDTO.expiresAt());
        transfer.setAmount(transferRequestDTO.amount());
        transfer.setCreatedAt(LocalDate.now());
        transfer.setStatus(TransferStatus.OPEN);

        return transferMapper.toTransferDTO(transferRequestRepository.save(transfer));
    }

    public List<TransferDTO> findAll(Long playerId, Long sourceTeamId, Long targetTeamId){
        List<TransferRequestEntity> transfers;

        if (playerId != null){
            transfers = transferRequestRepository.findByPlayerId(playerId);
        } else if (sourceTeamId != null){
            transfers = transferRequestRepository.findBySourceTeamId(sourceTeamId);
        } else if (targetTeamId != null){
            transfers = transferRequestRepository.findByTargetTeamId(targetTeamId);
        } else {
            transfers = transferRequestRepository.findAll();
        }

        List<TransferDTO> result = new ArrayList<>();
        for (TransferRequestEntity transfer : transfers){
            result.add(transferMapper.toTransferDTO(transfer));
        }
        return result;

    }

    public TransferDTO rejectTransfer(Long id){
        TransferRequestEntity transfer = findEntityById(id);
        if(transfer.getStatus() != TransferStatus.OPEN){
            throw new ConflictException("Csak nyitott átigazolás utasítható el!");
        }
        transfer.setStatus(TransferStatus.REJECTED);
        return transferMapper.toTransferDTO(transferRequestRepository.save(transfer));
    }

    @Transactional
    public TransferDTO acceptTransfer(Long id){
        TransferRequestEntity transfer = findEntityById(id);
        long targetTeamSize = playerRepository.countByTeamId(transfer.getTargetTeam().getId());
        PlayerEntity player = transfer.getPlayer();
        TeamEntity targetTeam = transfer.getTargetTeam();
        TeamEntity sourceTeam = transfer.getSourceTeam();
        List<TransferRequestEntity> transferRequests = transferRequestRepository.findByPlayerIdAndStatus(player.getId(), TransferStatus.OPEN);


        if(transfer.getStatus() != TransferStatus.OPEN){
            throw new ConflictException("Csak nyitott átigazolás fogadható el!");
        }
        if(transfer.getExpiresAt().isBefore(LocalDate.now())){
            throw new ConflictException("Az átigazolás lejárt!");
        }
        if(targetTeamSize >= MAX_TEAM_SIZE){
            throw new ConflictException("A cél csapat kerete betelt!");
        }
        if(targetTeam.getBudget().compareTo(transfer.getAmount()) < 0){
            throw new ConflictException("Nincs elég pénz a forrás csapatban!");
        }

        targetTeam.setBudget(
                targetTeam.getBudget().subtract(transfer.getAmount()));
        if(sourceTeam != null){
            sourceTeam.setBudget(
                    sourceTeam.getBudget().add(transfer.getAmount()));
        }
        player.setTeam(targetTeam);
        player.setCaptain(false);
        transfer.setStatus(TransferStatus.ACCEPTED);

        for(TransferRequestEntity transferRequest : transferRequests){
            if(transferRequest.getId().equals(id)){
                continue;
            }
            transferRequest.setStatus(TransferStatus.REJECTED);
        }

        transferDocumentService.createDocument(transfer);
        return transferMapper.toTransferDTO(transfer);
    }



    private TransferRequestEntity findEntityById(Long id){
        return transferRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Az átigazolás nem található!"));
    }

    


}
