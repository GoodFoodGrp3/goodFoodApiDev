package com.goodfood.api.controller;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/admin/errorLogs" )
public class ErrorLogController
{
    @Autowired
    private ErrorLogServices errorLogServices;

    @GetMapping( value = "" )
    public List<ErrorLog> getErrorLogs()
    {
//        Status status = authenticationService.getCurrentEmployee().getStatus();
//        generatePrivilegeErrorIf( status != Status.ADMINISTRATEUR && status != Status.RESTAURATEUR );

        return errorLogServices.getErrorLogs();
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }

}