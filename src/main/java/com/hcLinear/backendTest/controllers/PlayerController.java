package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }

    public Optional<PlayerEntity> findById(Long id) {
        return playerService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlayerEntity create(@RequestBody PlayerEntity player) {
        return playerService.save(player);
    }

    @PutMapping
    public PlayerEntity update(@RequestBody PlayerEntity player) {
        return playerService.save(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.deleteById(id);
    }


}
