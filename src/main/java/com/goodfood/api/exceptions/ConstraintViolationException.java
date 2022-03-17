package com.goodfood.api.exceptions;

import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConstraintViolationException extends ResponseStatusException {

    @Autowired
    ErrorLogServices errorLogServices;

    public ConstraintViolationException( String s ) {
        super( HttpStatus.BAD_REQUEST, s );
    }

}
