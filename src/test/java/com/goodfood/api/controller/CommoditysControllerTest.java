package com.goodfood.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommoditysControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllCommoditys() throws Exception {

        mockMvc.perform(get("/commoditys")).andExpect(status().isOk());

    }

    @Test
    public void testGetCommodityById() throws  Exception {

        mockMvc.perform(get("/commoditys/1")).andExpect(status().isOk());
    }
}
