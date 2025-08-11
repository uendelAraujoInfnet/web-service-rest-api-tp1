package com.example.mathapi.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record OperationResponse (
        String operation,
        BigDecimal a,
        BigDecimal b,
        BigDecimal result,
        Instant timestapm
) {}
