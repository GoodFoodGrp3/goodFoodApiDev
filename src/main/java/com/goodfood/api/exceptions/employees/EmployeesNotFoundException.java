package com.goodfood.api.exceptions.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeesNotFoundException extends ResponseStatusException
{
    public EmployeesNotFoundException(String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
