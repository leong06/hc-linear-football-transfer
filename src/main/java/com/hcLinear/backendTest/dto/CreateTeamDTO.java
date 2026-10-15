package com.hcLinear.backendTest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateTeamDTO(

        @Schema(description = "A csapat neve", example = "Budapesti VSC")
        @NotBlank String name,
        @Schema(description = "A csapat városa", example = "Budapest")
        @NotBlank String city,
        @Schema(description = "A csapat létrehozásának éve", example = "1900")
        @NotNull Integer foundedYear,
        @Schema(description = "A csapat büdzséje", example = "100000000")
        @NotNull @PositiveOrZero BigDecimal budget
) {
}
