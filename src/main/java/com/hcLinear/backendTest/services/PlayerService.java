package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.CreatePlayerDTO;
import com.hcLinear.backendTest.dto.PlayerDTO;
import com.hcLinear.backendTest.dto.PlayerMapper;
import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.exceptions.ConflictException;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.PlayerRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final PlayerMapper playerMapper;
    private static final int MIN_AGE = 15;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDTO> findAll() {
        List<PlayerDTO> players = new ArrayList<>();
        for(PlayerEntity player : playerRepository.findAll()) {
            players.add(playerMapper.toPlayerDTO(player));
        }

        return players;
    }

    public PlayerDTO findById(Long id) {
        PlayerEntity player = playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nem található a játékos a " + id + " azonosítóval!"));
        return playerMapper.toPlayerDTO(player);
    }

    public PlayerDTO save(CreatePlayerDTO createPlayerDTO)
    {
        PlayerEntity player = playerMapper.toPlayerEntity(createPlayerDTO);
        int age = Period.between(player.getBirthDate(), LocalDate.now()).getYears();
        if (age < MIN_AGE) {
            throw new ValidationException("A játékosnak legalább " + MIN_AGE + " évesnek kell lennie!");
        }

        if (player.getTeam() != null) {
            player.setTeam(teamService.findEntityById(player.getTeam().getId()));
        }

        if(player.isCaptain()) {
            if(player.getTeam() == null){
                throw new ValidationException("Csapat nélküli játékos nem lehet csapatkapitány!");
            }
            if(playerRepository.existsByTeamIdAndCaptainTrue(player.getTeam().getId())){
                throw new ConflictException("Csapatban már van csapatkapitány!");
            }
        }

        return playerMapper.toPlayerDTO(playerRepository.save(player));
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
    


}
