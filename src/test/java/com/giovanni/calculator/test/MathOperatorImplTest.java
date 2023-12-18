package com.giovanni.calculator.test;

import com.giovanni.calculator.exception.InvalidOperationException;
import com.giovanni.calculator.service.MathOperatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathOperatorImplTest {

    private MathOperatorImpl mathOperator;

    @BeforeEach
    public void setUp() {
        mathOperator = new MathOperatorImpl();
    }

    @Test
    public void testMultiply() throws InvalidOperationException {
        double result = mathOperator.doMath(5, 4, "*");
        assert result == 20.0;
    }

    @Test
    public void testDivide() throws InvalidOperationException {
        double result = mathOperator.doMath(8, 4, "/");
        assert result == 2.0;
    }

    @Test
    public void testAdd() throws InvalidOperationException {
        double result = mathOperator.doMath(3, 7, "+");
        assert result == 10.0;
    }

    @Test
    public void testSubtract() throws InvalidOperationException {
        double result = mathOperator.doMath(10, 4, "-");
        assert result == 6.0;
    }

    @Test
    public void testDivideByZero() {
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(2, 0, "/"));
    }

    @Test
    public void testUnknownOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(2, 3, "^"));
    }
}
