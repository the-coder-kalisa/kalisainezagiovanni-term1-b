package com.giovanni.calculator.controller;

import com.giovanni.calculator.dto.DoMathRequest;
import com.giovanni.calculator.exception.InvalidOperationException;
import com.giovanni.calculator.service.MathOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathOperator mathOperator;

    @Autowired
    public MathController(MathOperator mathOperator) {
        this.mathOperator = mathOperator;
    }

    @PostMapping("/doMath")
    public ResponseEntity<Map<String, Double>> doMath(@RequestBody DoMathRequest request) {
        try {
            double result = mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
            Map<String, Double> response = Collections.singletonMap("calcResponse", result);
            return ResponseEntity.ok(response);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyMap());
        }
    }
}
