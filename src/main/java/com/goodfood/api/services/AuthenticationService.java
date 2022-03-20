package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Employees getCurrentUser();

    Authentication authentication(String username, String password);

}
