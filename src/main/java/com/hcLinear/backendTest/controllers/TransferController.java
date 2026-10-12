package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.CreateTransferDTO;
import com.hcLinear.backendTest.dto.TransferDTO;
import com.hcLinear.backendTest.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
