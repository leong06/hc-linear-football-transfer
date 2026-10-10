package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.entities.TeamEntity;
import com.hcLinear.backendTest.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamEntity> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamEntity findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TeamEntity create(@RequestBody TeamEntity team) {
        return teamService.save(team);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.deleteById(id);
    }



}
