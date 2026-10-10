package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.PlayerRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private static final int MIN_AGE = 15;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerEntity> findAll() {
        return playerRepository.findAll();
    }

    public PlayerEntity findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nem található a játékos a " + id + "azonosítóval!"));
    }

    public PlayerEntity save(PlayerEntity player)
    {
        int age = Period.between(player.getBirthDate(), LocalDate.now()).getYears();
        if (age < MIN_AGE) {
            throw new ValidationException("A játékosnak legalább 15 évesnek kell lennie!");
        }
        return playerRepository.save(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
    


}
