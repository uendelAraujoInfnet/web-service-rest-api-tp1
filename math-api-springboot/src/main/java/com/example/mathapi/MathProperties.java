package com.example.mathapi;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.math.RoundingMode;

@Validated
@ConfigurationProperties(prefix = "app.math")
public class MathProperties {
    @Min(0) private int scale = 8;
    private RoundingMode rouding = RoundingMode.HALF_UP;
}
