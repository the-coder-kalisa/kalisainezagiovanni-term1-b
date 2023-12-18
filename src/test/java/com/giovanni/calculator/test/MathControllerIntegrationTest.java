package com.giovanni.calculator.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDoMathEndpoint() throws Exception {
        String requestBody = "{\"operand1\": 1.0, \"operand2\": 2.0, \"operation\": \"+\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/math/doMath").content(
                requestBody).contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.calcResponse").value(3.0))
                .andReturn();
    }

}
