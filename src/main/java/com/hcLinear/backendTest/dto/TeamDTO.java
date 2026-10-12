package com.hcLinear.backendTest.dto;

import java.math.BigInteger;

public record TeamDTO(
        Long id,
        String name,
        String city,
        int foundedYear,
        BigInteger budget,
        long playerCount,
        String captainName
) {
}
