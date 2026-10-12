package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.dto.CreatePlayerDTO;
import com.hcLinear.backendTest.dto.PlayerDTO;
import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Játékosok", description = "Játékosok kezelésére szolgáló REST API")
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

    @Operation(summary = "Játékos lekérdezése azonosító alapján", description = "Visszaadja a megadott azonosítójú játékost")
    @ApiResponse(responseCode = "200", description = "A megadott azonosítójú játékos megtalálva")
    @ApiResponse(responseCode = "404", description = "A megadott azonosítójú játékos nem található")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerDTO findById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlayerDTO create(@Valid @RequestBody CreatePlayerDTO player) {
        return playerService.save(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.deleteById(id);
    }


}
