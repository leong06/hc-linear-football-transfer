package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.Position;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePlayerDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull Position position,
        @NotNull @Min(1) @Max(99) Integer shirtNumber,
        @NotNull LocalDate birthDate,
        @NotNull @PositiveOrZero BigDecimal marketValue,
        Long teamId,
        boolean captain
) {
}
