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
public class EmployeesControllerTest
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
    public void testGetAllEmployees() throws Exception
    {
        mockMvc.perform(get("/employees").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws  Exception
    {
        mockMvc.perform(get("/employees/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeByIdError() throws  Exception
    {
        mockMvc.perform(get("/employees/50").header("Authorization", "Bearer " + token))
                .andExpect(status().is(403));
    }
}
