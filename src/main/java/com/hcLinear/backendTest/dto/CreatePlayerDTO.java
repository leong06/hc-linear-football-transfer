package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePlayerDTO(
        @Schema(description = "A játékos keresztneve", example = "Elek")
        @NotBlank String firstName,

        @Schema(description = "A játékos vezetékneve", example = "Teszt")
        @NotBlank String lastName,
        @Schema(description = "A játékos pozíciója", example = "FW")
        @NotNull Position position,
        @Schema(description = "A játékos mezszáma", example = "10")
        @NotNull @Min(1) @Max(99) Integer shirtNumber,

        @Schema(description = "A játékos születési dátuma", example = "2003-04-15")
        @NotNull LocalDate birthDate,
        @Schema(description = "A játékos piacértéke", example = "1000000")
        @NotNull @PositiveOrZero BigDecimal marketValue,
        Long teamId,
        boolean captain
) {
}
