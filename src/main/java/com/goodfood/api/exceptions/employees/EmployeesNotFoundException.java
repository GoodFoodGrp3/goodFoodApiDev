package com.goodfood.api.exceptions.employees;

import com.goodfood.api.exceptions.customers.CustomersValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux employers.
 * </p>
 * @author Gaëtan T.
 */
public class EmployeesNotFoundException extends ResponseStatusException
{
    public EmployeesNotFoundException(String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
