package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.Employees;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication authentication(String username, String password);

    Employees getCurrentUser();

    Customers getCurrentCustomer();

    //////////////////////////////////////

    String loginEmployees(Employees user);

    String loginCustomers(Customers user);

}
