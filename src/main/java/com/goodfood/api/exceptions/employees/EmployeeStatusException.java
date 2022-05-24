package com.goodfood.api.exceptions.employees;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux employers.
 * </p>
 * @exception EmployeeStatusException si employer n'a pas le bon status.
 * @author Gaëtan T.
 */
public class EmployeeStatusException extends ResponseStatusException
{
    public EmployeeStatusException()
    {
        super( HttpStatus.FORBIDDEN, "You have not the right authorities." );
    }
}
