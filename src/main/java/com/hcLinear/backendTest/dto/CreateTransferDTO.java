package com.hcLinear.backendTest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransferDTO(
        @Schema(description = "A tranzakcióban részt vevő játékos azonosítója", example = "1")
        @NotNull Long playerId,

        @Schema(description = "A tranzakcióban részt vevő célcsapat azonosítója", example = "2")
        @NotNull Long targetTeamId,

        @Schema(description = "A tranzakció lejárati dátuma", example = "2023-01-01")
        @NotNull LocalDate expiresAt,

        @Schema(description = "A tranzakció összege", example = "100000000")
        @PositiveOrZero @NotNull BigDecimal amount
) {
}
