package com.example.mathapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BigDecimalConverter implements Converter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String source) {
        if (source == null) return null;
        // aceita "1,5" e "1.5"
        return new BigDecimal(source.replace(",", "."));
    }
}
