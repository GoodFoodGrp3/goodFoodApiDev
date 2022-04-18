package com.goodfood.api.exceptions.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomersValidationException extends ResponseStatusException
{
    public CustomersValidationException(String s)
    {
        super(HttpStatus.BAD_REQUEST, s);
    }
}
