package com.goodfood.api.entities;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.Employees;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable
{
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

/*    private Employees user;
    private Customers customers;
    private Collection<? extends GrantedAuthority> authorities;*/



    // ***************
    // CONSTRUCTOR
    // ***************

    public JwtResponse(String jwttoken)
    {
        this.jwttoken = jwttoken;
    }


    public String getToken() {
        return this.jwttoken;
    }

    /*public JwtResponse(Employees user, String token, Collection<? extends GrantedAuthority> authorities)
    {
        this.user = user;
        this.token = token;
        this.authorities = authorities;
    }

    public JwtResponse(Customers customers,String token, Collection<? extends GrantedAuthority> authorities)
    {
        this.token = token;
        this.customers = customers;
        this.authorities = authorities;
    }*/

    /*public Employees getUser()
    {
        return user;
    }

    public String getToken()
    {
        return token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public Customers getCustomers()
    {
        return customers;
    }*/
}
