package com.goodfood.api.controller;

import com.goodfood.api.request.LoginForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest
{
    @Autowired
    EmployeesController employeesController;

    String token;

    @BeforeEach
    public void setUp()
    {
        LoginForm loginform = new LoginForm("gaetan","test");

        token = employeesController.login(loginform, null).getBody().getToken();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProducts() throws Exception
    {
        mockMvc.perform(get("/products").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductById() throws  Exception
    {
        mockMvc.perform(get("/products/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductByIdError() throws  Exception
    {
        mockMvc.perform(get("/products/50").header("Authorization", "Bearer " + token))
                .andExpect(status().is(404));
    }
}
