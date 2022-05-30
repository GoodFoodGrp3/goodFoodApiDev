package com.goodfood.api.exceptions.offices;

import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux offices.
 * </p>
 * @author Gaëtan T.
 */
public class OfficesNotFoundException extends ResponseStatusException
{
    public OfficesNotFoundException( String s )
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
