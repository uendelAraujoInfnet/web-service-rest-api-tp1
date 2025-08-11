package com.example.mathapi.controller;

import com.example.mathapi.dto.OperationRequest;
import com.example.mathapi.dto.OperationResponse;
import com.example.mathapi.service.MathService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1/math")
public class MathController {

    private final MathService math;

    public MathController(MathService math){
        this.math = math;
    }

    /* ------------------------ ADD ---------------------*/
    @RequestMapping(value = "/add", method = GET)
    public OperationResponse addGet(@RequestParam BigDecimal a, @RequestParam BigDecimal b){
        return build("add", a, b, math.add(a,b));
    }

    @RequestMapping(value = "/add", method = POST)
    public OperationResponse addPost(@RequestBody OperationRequest body) {
        return build("add", body.a(), body.b(), math.add(body.a(), body.b()));
    }

    // ---------- SUBTRACT ----------
    @RequestMapping(value = "/subtract", method = GET)
    public OperationResponse subtractGet(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return build("subtract", a, b, math.subtract(a, b));
    }

    @RequestMapping(value = "/subtract", method = POST)
    public OperationResponse subtractPost(@RequestBody OperationRequest body) {
        return build("subtract", body.a(), body.b(), math.subtract(body.a(), body.b()));
    }

    // ---------- MULTIPLY ----------
    @RequestMapping(value = "/multiply", method = GET)
    public OperationResponse multiplyGet(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return build("multiply", a, b, math.multiply(a, b));
    }

    @RequestMapping(value = "/multiply", method = POST)
    public OperationResponse multiplyPost(@RequestBody OperationRequest body) {
        return build("multiply", body.a(), body.b(), math.multiply(body.a(), body.b()));
    }

    // ---------- DIVIDE ----------
    @RequestMapping(value = "/divide", method = GET)
    public OperationResponse divideGet(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return build("divide", a, b, math.divide(a, b));
    }

    @RequestMapping(value = "/divide", method = POST)
    public OperationResponse dividePost(@RequestBody OperationRequest body) {
        return build("divide", body.a(), body.b(), math.divide(body.a(), body.b()));
    }

    // ---------- POW ----------
    @RequestMapping(value = "/pow", method = GET)
    public OperationResponse powGet(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return build("pow", a, b, math.pow(a, b));
    }

    @RequestMapping(value = "/pow", method = POST)
    public OperationResponse powPost(@RequestBody OperationRequest body) {
        return build("pow", body.a(), body.b(), math.pow(body.a(), body.b()));
    }

    private OperationResponse build(String op, BigDecimal a, BigDecimal b, BigDecimal result) {
        return new OperationResponse(op, a, b, result, Instant.now());
    }
}
