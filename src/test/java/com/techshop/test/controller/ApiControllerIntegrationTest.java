package com.techshop.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techshop.dto.CreateTechProduct;
import com.techshop.test.annotation.IntegrationTest;
import com.techshop.test.mocks.MockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
public class ApiControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "test", password = "pass")
    void shouldGetOk_whenPutNewTechProduct() throws Exception {
        CreateTechProduct request = MockData.createTechProduct();
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                put("/api/product/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.id").value(MockData.TechProduct.ID))
            .andExpect(jsonPath("$.data.name").value(MockData.TechProduct.NAME))
            .andExpect(jsonPath("$.data.type.id").value(MockData.Type.ID))
            .andExpect(jsonPath("$.data.type.name").value(MockData.Type.NAME));
    }

    @Test
    void shouldGetUnauthorized_whenNoUser() throws Exception {
        CreateTechProduct product = MockData.createTechProduct();
        String content = objectMapper.writeValueAsString(product);

        mvc.perform(
                put("/api/product/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isUnauthorized());
    }
}
