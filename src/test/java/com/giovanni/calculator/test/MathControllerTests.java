package com.giovanni.calculator.test;

import com.giovanni.calculator.controller.MathController;
import com.giovanni.calculator.dto.DoMathRequest;
import com.giovanni.calculator.service.MathOperator;
import com.giovanni.calculator.exception.InvalidOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTests {

    private MathController mathController;

    @Mock
    private MathOperator mathOperator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mathController = new MathController(mathOperator);
    }

    @Test
    void testDoMath_Success() {
        // Arrange
        DoMathRequest request = new DoMathRequest(10, 5, "+");
        double expectedResult = 15.0;
        when(mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation()))
                .thenReturn(expectedResult);

        // Act
        ResponseEntity<Map<String, Double>> response = mathController.doMath(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("calcResponse", expectedResult), response.getBody());
        verify(mathOperator, times(1)).doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
    }

    @Test
    void testDoMath_InvalidOperation() {
        // Arrange
        DoMathRequest request = new DoMathRequest(10, 0, "/");
        when(mathOperator.doMath(request.getOperand1(), request.getOperand2(), request.getOperation()))
                .thenThrow(new InvalidOperationException("Invalid operation")); // Add constructor argument

        // Act
        ResponseEntity<Map<String, Double>> response = mathController.doMath(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.emptyMap(), response.getBody());
        verify(mathOperator, times(1)).doMath(request.getOperand1(), request.getOperand2(), request.getOperation());
    }
}