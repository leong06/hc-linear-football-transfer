package com.hcLinear.backendTest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransferDTO(
        @NotNull
        Long playerId,
        @NotNull
        Long targetTeamId,
        @NotNull
        LocalDate expiresAt,
        @PositiveOrZero
        @NotNull
        BigDecimal amount
) {
}
