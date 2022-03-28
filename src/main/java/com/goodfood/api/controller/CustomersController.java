package com.goodfood.api.controller;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private ErrorLogServices errorLogServices;

    // Get a Member by its username
    @GetMapping( value = "/profile/search/{username}" )
    public Customers getCustomerByUsername(@PathVariable String username ) {
        return customersService.getCustomerByUserName( username );
    }

    @GetMapping(value = "")
    public List<Customers> getAllCustomers(){
        return this.customersService.getAllCustomers();
    }

    @GetMapping( value = "/{id}" )
    public Customers getCustomerById( @PathVariable int id ) {
        return this.customersService.getCustomerById( id );
    }

    @PostMapping( value = "/register" )
    public ResponseEntity<Customers> registerCustomer(@Valid @RequestBody RegisterCustomerForm registerCustomerForm, Errors errors,
                                                      HttpServletRequest request ) {

        constraintViolationCheck( errors, request );

        return new ResponseEntity<Customers>( customersService.registerCustomer(registerCustomerForm), HttpStatus.OK );
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck( Errors errors, HttpServletRequest request ) {

        if ( errors.hasErrors() ) {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();
            for ( ObjectError e : errors.getAllErrors() ) {
                violationsList.add( e.unwrap( ConstraintViolation.class ) );
            }
            String exceptionMessage = "";
            for ( ConstraintViolation<?> violation : violationsList ) {
                if ( violationsList.indexOf( violation ) > 0 ) {
                    exceptionMessage += " | ";
                }
                exceptionMessage += violation.getMessage();
            }
            errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.BAD_REQUEST, exceptionMessage ) );
            throw new ConstraintViolationException( exceptionMessage );
        }
    }
}
