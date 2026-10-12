package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.CreateTransferDTO;
import com.hcLinear.backendTest.dto.TransferDTO;
import com.hcLinear.backendTest.dto.TransferMapper;
import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.entities.TeamEntity;
import com.hcLinear.backendTest.entities.TransferRequestEntity;
import com.hcLinear.backendTest.entities.TransferStatus;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.TransferRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private final TransferRequestRepository transferRequestRepository;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final TransferMapper transferMapper;

    public TransferService(TransferRequestRepository transferRequestRepository, PlayerService playerService, TeamService teamService, TransferMapper transferMapper) {
        this.transferRequestRepository = transferRequestRepository;
        this.playerService = playerService;
        this.teamService = teamService;
        this.transferMapper = transferMapper;
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

}
