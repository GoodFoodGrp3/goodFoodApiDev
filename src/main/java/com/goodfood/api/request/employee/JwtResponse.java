package com.goodfood.api.request.employee;

import com.goodfood.api.entities.Employees;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private Employees user;

    private String token;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse() {
    }

    public JwtResponse(Employees user, String token, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.token = token;
        this.authorities = authorities;
    }

    public Employees getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}