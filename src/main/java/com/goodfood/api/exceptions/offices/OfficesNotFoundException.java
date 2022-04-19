package com.goodfood.api.exceptions.offices;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OfficesNotFoundException extends ResponseStatusException
{
    public OfficesNotFoundException( String s )
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
