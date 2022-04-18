package com.goodfood.api.controller;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.request.employee.JwtResponse;
import com.goodfood.api.request.LoginForm;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.ErrorLogServices;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private AuthenticationService authentificationService;

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
    public ResponseEntity<Customers> registerCustomer(@Valid @RequestBody RegisterCustomerForm registerCustomerForm) {

        //constraintViolationCheck( errors, request );

        return new ResponseEntity<Customers>( customersService.registerCustomer(registerCustomerForm), HttpStatus.OK );
    }

    private static final long BLOCKED_ACCOUNT_DURATION = 30 * 60 * 1000L; // in milliseconds - 30 minutes

    @PostMapping( value = "/login" )
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm credentials, HttpServletRequest request ) {

        final Authentication authentication;

        Customers customers;
        customers = null;

        try {
            customers = this.customersService.getCustomerByUserName(credentials.getUsername());

            if ( customers != null ) {

                if ( customers.isIs_blocked() ) {

                    Timestamp now = new Timestamp( new DateTime().getMillis() );
                    long duration = now.getTime() - customers.getBlocked_date().getTime();
                    long timeLeft = 9999;

                    if ( duration < BLOCKED_ACCOUNT_DURATION ) {
                        timeLeft = BLOCKED_ACCOUNT_DURATION - duration;
                    }

                    else {
                        timeLeft = 0;
                        customers.setBlocked_date( null );
                        customers.setIs_blocked( false );
                        customers.setCounter( 3 );
                    }

                    if ( timeLeft > 0 ) {
                        timeLeft = timeLeft / 1000 / 60;
                        /*errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." ) );*/
                        throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." );
                    }
                }
            }

            authentication = this.authentificationService.authentication( credentials.getUsername(),
                    credentials.getPassword() );

            customers.setCounter( 3 );
            customersRepository.save( customers ); // update of counter

        } catch ( AuthenticationException e ) {

            if ( customers != null ) {

                customers.setCounter( customers.getCounter() - 1 );

                if ( customers.getCounter() == 0 ) {

                    customers.setIs_blocked( true );
                    customers.setBlocked_date( new Timestamp( new DateTime().getMillis() ) );

                }

                errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + customers.getCounter() ) );
                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + customers.getCounter() );
            }

            errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator." ) );
            throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator." );

        }

        customers.setCounter( 3 );
        final Customers customer = (Customers) authentication.getPrincipal();
        customer.setCounter( 3 );
        final String token = this.authentificationService.loginCustomers( customer );
        return new ResponseEntity<>( new JwtResponse( customer, token, authentication.getAuthorities() ), HttpStatus.OK );
    }

    // Delete member
    @DeleteMapping( value = "/profile/{id}" )
    public void deleteCustomerById( @PathVariable int id ) {

        /*Status status = authentificationService.getCurrentUser().getStatus();
        generatePrivilegeErrorIf( status != Status.ADMINISTRATOR );*/

        customersService.deleteById( id );
    }

    @PutMapping( value = "/profile/{id}" )
    public Customers updateCustomerById(@PathVariable int id, @Valid @RequestBody UpdateCustomerForm updateCustomerForm) {
        //constraintViolationCheck( errors, request );

       /* Employees currentUser = authentificationService.getCurrentUser();
        generatePrivilegeErrorIf( currentUser.getId() != id );*/

        return customersService.updateCustomerProfile( id, updateCustomerForm );
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
