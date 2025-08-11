package com.example.mathapi;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.math.RoundingMode;

@Validated
@ConfigurationProperties(prefix = "app.math")
public class MathProperties {
    @Min(0) private int scale = 2;
    private RoundingMode rouding = RoundingMode.HALF_UP;

    public int getScale() { return scale; }
    public void setScale(int scale) {this.scale = scale;}

    public RoundingMode getRouding() { return rouding; }
    public void setRouding(RoundingMode rouding) {this.rouding = rouding;}
}
