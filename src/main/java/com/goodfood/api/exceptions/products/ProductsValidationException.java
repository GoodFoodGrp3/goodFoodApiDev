package com.goodfood.api.exceptions.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductsValidationException extends ResponseStatusException
{
    public ProductsValidationException( String s ) {
        super( HttpStatus.BAD_REQUEST, s );
    }

}
