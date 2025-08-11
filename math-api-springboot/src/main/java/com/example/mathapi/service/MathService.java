package com.example.mathapi.service;

import com.example.mathapi.MathProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class MathService {

    private final MathProperties props;

    public MathService(MathProperties props){
        this.props = props;
    }

    private BigDecimal round(BigDecimal value){
        return value.setScale(props.getScale(), props.getRouding());
    }

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return round(a.add(b));
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return round(a.subtract(b));
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return round(a.multiply(b));
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if(b.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return a.divide(b, props.getScale(), props.getRouding());
    }

    // Potenciação com expoente inteiro (positivo, zero ou negativo)
    public BigDecimal pow(BigDecimal a, BigDecimal b) {
        try {
            int exp = b.intValueExact(); // garante que seja inteiro exato
            if (exp >= 0) {
                BigDecimal result = a.pow(exp, new MathContext(props.getScale(), props.getRouding()));
                return round(result);
            } else {
                int abs = Math.abs(exp);
                BigDecimal positive = a.pow(abs, new MathContext(props.getScale(), props.getRouding()));
                BigDecimal result = BigDecimal.ONE.divide(positive, props.getScale(), props.getRouding());
                return round(result);
            }
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Expoente deve ser um número inteiro (sem casas decimais).");
        }
    }
}
