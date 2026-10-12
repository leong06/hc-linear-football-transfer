package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.dto.PlayerDTO;
import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerDTO findById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlayerEntity create(@Valid @RequestBody PlayerEntity player) {
        return playerService.save(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.deleteById(id);
    }


}
