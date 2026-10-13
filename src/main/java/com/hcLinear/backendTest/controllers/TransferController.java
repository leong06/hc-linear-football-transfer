package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.CreateTransferDTO;
import com.hcLinear.backendTest.dto.TransferDTO;
import com.hcLinear.backendTest.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TransferDTO createTransfer(@Valid @RequestBody CreateTransferDTO request) {
        return transferService.createTransfer(request);
    }

    @GetMapping
    public List<TransferDTO> findAll(
        @RequestParam(required = false) Long playerId,
        @RequestParam(required = false) Long sourceTeamId,
        @RequestParam(required = false) Long targetTeamId){
        return transferService.findAll(playerId, sourceTeamId, targetTeamId);
    }

    @PostMapping("/{id}/reject")
    public TransferDTO rejectTransfer(@PathVariable Long id){
        return transferService.rejectTransfer(id);
    }

    @PostMapping("/{id}/accept")
    public TransferDTO acceptTransfer(@PathVariable Long id){
        return transferService.acceptTransfer(id);
    }




}
