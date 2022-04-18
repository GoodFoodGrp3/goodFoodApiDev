package com.goodfood.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeValidationException extends ResponseStatusException
{
    public EmployeeValidationException(String s)
    {
        super( HttpStatus.BAD_REQUEST, s);
    }
}
