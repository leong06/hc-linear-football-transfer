package com.hcLinear.backendTest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateTeamDTO(
        @NotBlank String name,
        @NotBlank String city,
        @NotNull Integer foundedYear,
        @NotNull @PositiveOrZero BigDecimal budget
) {
}
