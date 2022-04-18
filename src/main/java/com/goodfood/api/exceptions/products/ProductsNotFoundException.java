package com.goodfood.api.exceptions.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductsNotFoundException extends ResponseStatusException
{
    public ProductsNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
