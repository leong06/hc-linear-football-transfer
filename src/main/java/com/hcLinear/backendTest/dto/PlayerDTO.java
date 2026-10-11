package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PlayerDTO(
        Long id,
        String fullName,
        Position position,
        int shirtNumber,
        LocalDate birthDate,
        BigDecimal marketValue,
        boolean captain,
        String teamName
) {
}
