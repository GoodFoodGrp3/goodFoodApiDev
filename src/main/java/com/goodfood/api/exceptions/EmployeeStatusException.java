package com.goodfood.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeStatusException extends ResponseStatusException {

    public EmployeeStatusException() {
        super( HttpStatus.FORBIDDEN, "You have not the right authorities." );
    }

}
