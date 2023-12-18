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
public class CalculatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDoMathEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/math/doMath"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Add more assertions as needed
                .andReturn();
    }
}
