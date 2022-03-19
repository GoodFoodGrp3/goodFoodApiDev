package com.goodfood.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllComment() throws Exception {

        mockMvc.perform(get("/comments")).andExpect(status().isOk());

    }

    @Test
    public void testGetCommentById() throws  Exception {

        mockMvc.perform(get("/comments/1")).andExpect(status().isOk());
    }

}
