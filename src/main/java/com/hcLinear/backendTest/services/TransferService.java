package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.TransferMapper;
import com.hcLinear.backendTest.repositories.TransferRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRequestRepository transferRequestRepository;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final TransferMapper transferMapper;

    public TransferService(TransferRequestRepository transferRequestRepository, PlayerService playerService, TeamService teamService, TransferMapper transferMapper) {
        this.transferRequestRepository = transferRequestRepository;
        this.playerService = playerService;
        this.teamService = teamService;
        this.transferMapper = transferMapper;
    }

    

}
