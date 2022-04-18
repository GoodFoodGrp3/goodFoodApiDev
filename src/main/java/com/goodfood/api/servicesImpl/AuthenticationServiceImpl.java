package com.goodfood.api.servicesImpl;

import com.auth0.jwt.JWT;
import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.security.SecurityConstants;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private CustomersService customersService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Authentication authentication(String username, String password)
    {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
    }

    @Override
    public String loginEmployees(Employees employees)
    {
        return JWT.create()
                .withSubject(employees.getFirstname())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    }


    @Override
	public Employees getCurrentUser()
    {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.employeesService.getEmployeesByFirstName((String) authentication.getPrincipal());
		
	}


    @Override
    public String loginCustomers(Customers customers)
    {
        return JWT.create()
                .withSubject(customers.getCustomername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    }


    @Override
    public Customers getCurrentCustomer()
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.customersService.getCustomerByUserName((String) authentication.getPrincipal());
    }
}
