package com.goodfood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodfood.api.request.CreateCommentForm;
import com.goodfood.api.request.LoginForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest
{
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JwtAuthenticationController jwtAuthenticationController;

   /* @Autowired
    EmployeesController employeesController;*/

    Object token;
    String token2;

/*    @BeforeEach
    public void setUp()
    {
        LoginForm loginform = new LoginForm("gaetan.taltavull@gmail.com","test");

        try
        {
            token =  jwtAuthenticationController.createAuthenticationToken(loginform, null).getBody();
           //token = test
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

       *//* LoginForm loginform2 = new LoginForm("gaetan","test");

        token2 = employeesController.login(loginform2, null).getBody().getToken();*//*
    }*/

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllComment() throws Exception
    {
        mockMvc.perform(get("/comments").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCommentById() throws  Exception
    {
        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());
    }
/*
    @Test
    public void testGetCommentByIdError() throws Exception
    {
        mockMvc.perform(get("/comments/100").header("Authorization", "Bearer " + token))
                .andExpect(status().is(404));
    }

    @Test
    public void testCreateComment() throws Exception
    {
        CreateCommentForm createCommentForm = new CreateCommentForm(8,"test");
        mockMvc.perform(post("/comments").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(mapper.writeValueAsString(createCommentForm)))
                .andExpect( status().is( 200 ))
                .andExpect( jsonPath( "$.content", is("test")));
    }

    @Test
    public void testCreateCommentError() throws Exception
    {
        CreateCommentForm createCommentForm = new CreateCommentForm(0,"test");
        mockMvc.perform(post("/comments").header("Authorization", "Bearer " + token2)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(mapper.writeValueAsString(createCommentForm)))
                .andExpect( status().is( 404 ));
                //.andExpect( jsonPath( "$.content", is("test")));
    }*/
}
