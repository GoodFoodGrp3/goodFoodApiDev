package com.goodfood.api.exceptions.categorie;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategorieNotFoundException extends ResponseStatusException
{
    public CategorieNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
