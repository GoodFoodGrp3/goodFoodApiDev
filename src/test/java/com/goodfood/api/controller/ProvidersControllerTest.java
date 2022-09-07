package com.goodfood.api.controller;

import com.goodfood.api.request.LoginForm;
import com.goodfood.api.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProvidersControllerTest
{
    @Autowired
    EmployeesController employeesController;

    String token;
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
    public void testGetAllProviders() throws Exception
    {
        mockMvc.perform(get("/providers").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProviderById() throws  Exception
    {
        mockMvc.perform(get("/providers/1").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProviderByIdError() throws  Exception
    {
        mockMvc.perform(get("/providers/50").header("Authorization", "Bearer " + token))
                .andExpect(status().is(404));
    }
}
