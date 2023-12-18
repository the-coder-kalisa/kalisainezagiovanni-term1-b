package com.giovanni.calculator.service;

import com.giovanni.calculator.exception.InvalidOperationException;

public interface MathOperator {

    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}
