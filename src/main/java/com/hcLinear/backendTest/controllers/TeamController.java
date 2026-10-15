package com.hcLinear.backendTest.controllers;


import com.hcLinear.backendTest.dto.CreateTeamDTO;
import com.hcLinear.backendTest.dto.TeamDTO;
import com.hcLinear.backendTest.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Csapatok", description = "Csapatok kezelésére szolgáló REST API")
@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @Operation(summary = "Csapatok listázása", description = "Visszaadja az összes csapatot")
    @ApiResponse(responseCode = "200", description = "Csapatok listázása")
    @GetMapping
    public List<TeamDTO> findAll() {
        return teamService.findAll();
    }


    @Operation(summary = "Csapat lekérdezése azonosító alapján", description = "A megadott azonosítójú csapat lekérdezése")
    @ApiResponse(responseCode = "200", description = "Csapat lekérdezése sikeres")
    @ApiResponse(responseCode = "404", description = "A megadott azonosítójú csapat nem található")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO findById(@PathVariable Long id) {
        return teamService.findById(id);
    }


    @Operation(summary = "Csapat létrehozása", description = "Új csapat létrehozása")
    @ApiResponse(responseCode = "201", description = "Csapat létrehozva")
    @ApiResponse(responseCode = "400", description = "Érvénytelen adatok")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TeamDTO create(@Valid @RequestBody CreateTeamDTO team) {
        return teamService.save(team);
    }


    @Operation(summary = "Csapat törlése azonosító alapján", description = "A megadott azonosítójú csapat törlése")
    @ApiResponse(responseCode = "204", description = "Csapat törlése sikeres")
    @ApiResponse(responseCode = "404", description = "A megadott azonosítójú csapat nem található")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.deleteById(id);
    }



}
