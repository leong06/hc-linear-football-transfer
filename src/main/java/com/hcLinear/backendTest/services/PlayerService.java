package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

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

    public PlayerEntity save(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
    


}
