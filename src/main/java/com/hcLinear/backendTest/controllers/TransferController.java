package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.CreateTransferDTO;
import com.hcLinear.backendTest.dto.TransferDTO;
import com.hcLinear.backendTest.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Átigazolások", description = "Átigazolások kezelésére szolgáló REST API")
@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Operation(summary = "Átigazolási kérelem létrehozása", description = "Átigazolási kérelem létrehozása a megadott adatok alapján")
    @ApiResponse(responseCode = "201", description = "Átigazolás létrehozva")
    @ApiResponse(responseCode = "400", description = "Érvénytelen adatok")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TransferDTO createTransfer(@Valid @RequestBody CreateTransferDTO request) {
        return transferService.createTransfer(request);
    }

    @Operation(summary = "Átigazolási kérelmek listázása", description = "Átigazolási kérelmek listázása a megadott paraméterek alapján")
    @GetMapping
    public List<TransferDTO> findAll(
       @Parameter(description = "Játékos azonosító") @RequestParam(required = false) Long playerId,
       @Parameter(description = "Forrás csapat azonosító") @RequestParam(required = false) Long sourceTeamId,
       @Parameter(description = "Cél csapat azonosító") @RequestParam(required = false) Long targetTeamId){
        return transferService.findAll(playerId, sourceTeamId, targetTeamId);
    }



    @Operation(summary = "Átigazolási kérelem elutasítása", description = "Átigazolás elutasítása az adott azonosítójú átigazolásra")
    @ApiResponse(responseCode = "200", description = "Átigazolás elutasítva")
    @ApiResponse(responseCode = "404", description = "Átigazolás nem található")
    @PostMapping("/{id}/reject")
    public TransferDTO rejectTransfer(@PathVariable Long id){
        return transferService.rejectTransfer(id);
    }



    @Operation(summary = "Átigazolási kérelem elfogadása", description = "Átigazolás elfogadása az adott azonosítójú átigazolásra, a játékos a célcsapatba kerül és a többi nyitott kérelem elutasításra kerül,"
            + "majd átigazolási dokumentum jön létre.")
    @ApiResponse(responseCode = "200", description = "Átigazolás elfogadva")
    @ApiResponse(responseCode = "404", description = "Átigazolás nem található")
    @ApiResponse(responseCode = "409", description = "Az alábbiak egyike: a kérelem nem nyitott, lejárt, a cél csapat kerete már betelt, nincs elég költségkeret.")
    @PostMapping("/{id}/accept")
    public TransferDTO acceptTransfer(@PathVariable Long id){
        return transferService.acceptTransfer(id);
    }




}
