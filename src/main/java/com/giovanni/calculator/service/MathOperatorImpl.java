package com.giovanni.calculator.service;

import com.giovanni.calculator.exception.InvalidOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathOperatorImpl implements MathOperator {

    @Override
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        switch (operation) {
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new InvalidOperationException("Cannot divide by 0");
                }
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            default:
                throw new RuntimeException("Unknown operation");
        }
    }
}
