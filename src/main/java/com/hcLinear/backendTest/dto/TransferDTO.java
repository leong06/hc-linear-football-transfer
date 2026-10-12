package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferDTO(
        Long id,
        Long playerId,
        String playerName,
        String sourceTeamName,
        Long sourceTeamId,
        String targetTeamName,
        Long targetTeamId,
        LocalDate createdAt,
        LocalDate expiresAt,
        BigDecimal amount,
        TransferStatus status
) {
}
