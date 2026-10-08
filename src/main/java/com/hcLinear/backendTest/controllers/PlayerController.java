package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }
    
}
