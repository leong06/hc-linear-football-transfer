package com.hcLinear.backendTest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferDocumentDTO(
        Long id,
        String documentNumber,
        LocalDateTime createdAt,
        Long transferRequestId,
        Long playerId,
        String playerName,
        Long sourceTeamId,
        String sourceTeamName,
        Long targetTeamId,
        String targetTeamName,
        BigDecimal amount
) {
}
