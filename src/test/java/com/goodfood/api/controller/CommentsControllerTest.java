package com.goodfood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodfood.api.request.CreateCommentForm;
import com.goodfood.api.request.LoginForm;
import com.goodfood.api.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;

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

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    public void setUp()
    {
        token = jwtTokenUtil.generateToken(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "test";
            }

            @Override
            public String getUsername() {
                return "gaetan.taltavull@gmail.com";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
//        LoginForm loginform = new LoginForm("gaetan","test");

//        token = employeesController.login(loginform, null).getBody().getToken();
    }

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
    @Test
    public void testGetCommentByIdError() throws Exception
    {
        mockMvc.perform(get("/comments/100").header("Authorization", "Bearer " + token))
                .andExpect(status().is(404));
    }

/*    @Test
    public void testCreateComment() throws Exception
    {
        CreateCommentForm createCommentForm = new CreateCommentForm(8,"test");
        mockMvc.perform(post("/comments").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(mapper.writeValueAsString(createCommentForm)))
                .andExpect( status().is( 200 ))
                .andExpect( jsonPath( "$.content", is("test")));
    }*/

 /*   @Test
    public void testCreateCommentError() throws Exception
    {
        CreateCommentForm createCommentForm = new CreateCommentForm(0,"test");
        mockMvc.perform(post("/comments").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON )
                        .content(mapper.writeValueAsString(createCommentForm)))
                .andExpect( status().is( 404 ));
                //.andExpect( jsonPath( "$.content", is("test")));
    }*/
}
