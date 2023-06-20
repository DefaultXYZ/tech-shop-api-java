package com.techshop.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techshop.controller.ApiController;
import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import com.techshop.dto.CreateTechProduct;
import com.techshop.service.TechProductService;
import com.techshop.test.annotation.ControllerTest;
import com.techshop.test.mocks.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest(importClasses = ApiController.class)
public class ApiControllerTest {
    private final TechProduct mockProduct = new TechProduct();

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TechProductService service;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(applicationContext)
            .apply(springSecurity())
            .build();
        mockService();
    }

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
            .andExpect(jsonPath("$.data.description").value(MockData.TechProduct.DESCRIPTION))
            .andExpect(jsonPath("$.data.cost").value(MockData.TechProduct.COST))
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

    private void mockService() {
        Type type = new Type();
        type.setId(MockData.Type.ID);
        type.setName(MockData.Type.NAME);
        mockProduct.setId(MockData.TechProduct.ID);
        mockProduct.setName(MockData.TechProduct.NAME);
        mockProduct.setType(type);
        mockProduct.setDescription(MockData.TechProduct.DESCRIPTION);
        mockProduct.setCost(MockData.TechProduct.COST);
        when(service.createNew(any())).thenReturn(mockProduct);
    }
}
