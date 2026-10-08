package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.PlayerEntity;
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

    public Optional<PlayerEntity> findById(Long id) {
        return playerRepository.findById(id);
    }

    public PlayerEntity save(PlayerEntity player) {
        return playerRepository.save(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
    


}
