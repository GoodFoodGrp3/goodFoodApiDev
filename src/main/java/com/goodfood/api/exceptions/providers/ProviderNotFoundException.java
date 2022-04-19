package com.goodfood.api.exceptions.providers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProviderNotFoundException extends ResponseStatusException
{
    public ProviderNotFoundException( String s )
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
