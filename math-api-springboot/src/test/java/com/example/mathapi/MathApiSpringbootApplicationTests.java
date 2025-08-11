package com.example.mathapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MathApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addGet_shouldReturnSum() throws Exception {
        mockMvc.perform(get("/api/v1/math/add?a=1.5&b=2.25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("3.75"));
    }

    @Test
    void powGet_negativeExponent_shouldWork() throws Exception {
        mockMvc.perform(get("/api/v1/math/pow?a=2&b=-3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("0.13"));
    }

    @Test
    void dividePost_byZero_should400() throws Exception {
        String body = "{\"a\": 10, \"b\": 0}";
        mockMvc.perform(post("/api/v1/math/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }
}
