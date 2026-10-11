package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.PlayerRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private static final int MIN_AGE = 15;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public List<PlayerEntity> findAll() {
        return playerRepository.findAll();
    }

    public PlayerEntity findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nem található a játékos a " + id + " azonosítóval!"));
    }

    public PlayerEntity save(PlayerEntity player)
    {
        int age = Period.between(player.getBirthDate(), LocalDate.now()).getYears();
        if (age < MIN_AGE) {
            throw new ValidationException("A játékosnak legalább " + MIN_AGE + " évesnek kell lennie!");
        }

        if (player.getTeam() != null) {
            player.setTeam(teamService.findById(player.getTeam().getId()));
        }
        
        return playerRepository.save(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
    


}
