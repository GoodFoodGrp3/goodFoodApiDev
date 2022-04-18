package com.goodfood.api.exceptions.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomersNotFoundException extends ResponseStatusException
{
    public CustomersNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
