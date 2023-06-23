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

@IntegrationTest(activeProfiles = {"admin", "test"})
public class AdminApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "test", password = "pass")
    void shouldGetOk_whenPutNewTechProduct() throws Exception {
        CreateTechProduct request = new CreateTechProduct("Name", MockData.TYPE_ID_11, "Desc", 1.1);
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                put("/api/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.id").exists())
            .andExpect(jsonPath("$.data.name").value("Name"))
            .andExpect(jsonPath("$.data.description").value("Desc"))
            .andExpect(jsonPath("$.data.cost").value(1.1))
            .andExpect(jsonPath("$.data.type.id").value(MockData.TYPE_ID_11))
            .andExpect(jsonPath("$.data.type.name").value(MockData.getTypeName(MockData.TYPE_ID_11)));
    }

    @Test
    void shouldGetUnauthorized_whenNoUser() throws Exception {
        CreateTechProduct request = new CreateTechProduct("Name", MockData.TYPE_ID_11, "Desc", 1.1);
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(
                put("/api/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isUnauthorized());
    }
}
