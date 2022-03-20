package com.goodfood.api.servicesImpl;

import com.auth0.jwt.JWT;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.security.SecurityConstants;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.EmployeesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import static com.goodfood.api.security.SecurityConstants.EXPIRATION_TIME;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final int SIZE_TMP_CODE = 10;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Authentication authentication(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
        );
    }

    @Override
    public String login(Employees user) {
        return JWT.create()
                .withSubject(user.getFirstname())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    }


	@Override
	public Employees getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.employeesService.getEmployeesByFirstName((String) authentication.getPrincipal());
		
	}
}
