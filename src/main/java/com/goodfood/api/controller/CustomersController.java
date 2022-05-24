package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
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
public class CustomersController
{
    // ***************
    // CONSTANTS
    // ***************

    private static final long BLOCKED_ACCOUNT_DURATION = 30 * 60 * 1000L; // in milliseconds - 30 minutes


    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private CustomersService customersService;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;



    // ***************
    // GET
    // ***************

    @GetMapping( value = "/profile/search/{username}" )
    public Customers getCustomerByUsername(@PathVariable String username )
    {
        return customersService.getCustomerByUserName(username);
    }

    @GetMapping(value = "")
    public List<Customers> getAllCustomers()
    {
        return this.customersService.getAllCustomers();
    }

    @GetMapping(value = "/status/{username}")
    public String getStatus(@PathVariable String username)
    {
        return this.customersService.getStatus( username );
    }

    @GetMapping( value = "/{id}" )
    public Customers getCustomerById( @PathVariable int id )
    {
        return this.customersService.getCustomerById(id);
    }

    @GetMapping( "/current" )
    public ResponseEntity<Customers> getCurrentCustomer() {
        return new ResponseEntity<>( this.customersService.getCurrentCustomer(), HttpStatus.OK );
    }

    // ***************
    // POST/REGISTER/LOGIN
    // ***************

    @PostMapping( value = "/register" )
    public ResponseEntity<Customers> registerCustomer(@Valid @RequestBody RegisterCustomerForm registerCustomerForm)
    {
        //constraintViolationCheck( errors, request );

        return new ResponseEntity<Customers>(customersService.registerCustomer(registerCustomerForm), HttpStatus.OK);
    }


    @DeleteMapping(value = "/profile/{id}")
    public void deleteCustomerById(@PathVariable int id)
    {
        this.customersService.deleteById(id);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @PutMapping(value = "/profile/{id}")
    public Customers updateCustomerById(@PathVariable int id, @Valid @RequestBody UpdateCustomerForm updateCustomerForm)
    {
        return customersService.updateCustomerProfile(id, updateCustomerForm);
    }

    @PutMapping(value = "/profile/{id}/password")
    public LoginDao updateCustomerPassword(@PathVariable int id,
                                           @RequestBody UpdateUserPasswordForm updateCustomerPassword)
    {

        return customersService.updatePassword(id, updateCustomerPassword);
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck( Errors errors, HttpServletRequest request )
    {
        if (errors.hasErrors())
        {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();

            for (ObjectError e : errors.getAllErrors())
            {
                violationsList.add( e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";

            for (ConstraintViolation<?> violation : violationsList)
            {
                if (violationsList.indexOf( violation) > 0 )
                {
                    exceptionMessage += " | ";
                }

                exceptionMessage += violation.getMessage();
            }

            errorLogServices.recordLog(new ErrorLog(request.getHeader("Host"), HttpStatus.BAD_REQUEST, exceptionMessage));
            throw new ConstraintViolationException(exceptionMessage);
        }
    }
}
