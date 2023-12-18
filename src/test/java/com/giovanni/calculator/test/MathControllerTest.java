package com.giovanni.calculator.test;

import com.giovanni.calculator.controller.MathController;
import com.giovanni.calculator.dto.DoMathRequest;
import com.giovanni.calculator.service.MathOperator;
import com.giovanni.calculator.exception.InvalidOperationException;

import static org.mockito.Mockito.when;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTest {

    private MathController mathController;

    @Mock
    private MathOperator mathOperator;

    @Test
    public void testDoMath_Success() throws InvalidOperationException {
        DoMathRequest request = new DoMathRequest(1.0, 2.0, "+");
        when(mathOperator.doMath(1.0, 2.0, "+")).thenReturn(3.0);
        ResponseEntity<?> response = mathController.doMath(request);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().equals(Map.of("calcResponse", 3.0));
    }

    @Test
    public void testDoMath_Fail() throws InvalidOperationException {
        DoMathRequest request = new DoMathRequest(1.0, 2.0, "+");
        when(mathOperator.doMath(1.0, 2.0, "+")).thenThrow(InvalidOperationException.class);
        ResponseEntity<?> response = mathController.doMath(request);
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert response.getBody() != null;
        assert response.getBody().equals(Map.of());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mathController = new MathController(mathOperator);
    }

}
