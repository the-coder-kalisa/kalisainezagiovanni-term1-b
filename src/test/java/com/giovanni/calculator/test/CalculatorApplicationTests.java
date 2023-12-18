package com.giovanni.calculator.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.giovanni.calculator.dto.DoMathRequest;
import java.util.Objects;

@SpringBootTest
public class CalculatorApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testDoMathEndpoint() throws Exception {
        double operand1 = 5;
        double operand2 = 4;
        String operation = "*";
        String url = "http://localhost:" + port + "/api/math/doMath";
        DoMathRequest request = new DoMathRequest(operand1, operand2, operation);
        ResponseEntity<Map<String, Double>> responseEntity = restTemplate.postForEntity

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(20.0, Objects.requireNonNull(responseEntity.getBody()).get("calcResponse"));
    }

}
