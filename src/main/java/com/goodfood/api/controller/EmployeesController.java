package com.goodfood.api.controller;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.exceptions.EmployeeStatusException;
import com.goodfood.api.repositories.EmployeesRepository;
import com.goodfood.api.request.JwtResponse;
import com.goodfood.api.request.member.LoginForm;
import com.goodfood.api.request.member.RegisterForm;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.EmployeesService;
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
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private AuthenticationService authentificationService;

    @Autowired
    private ErrorLogServices errorLogServices;

    private static final long BLOCKED_ACCOUNT_DURATION = 30 * 60 * 1000L; // in milliseconds - 30 minutes

    @GetMapping(value = "")
    public List<Employees> getAllEmployees(){
        return this.employeesService.getAllEmployees();
    }

    @PostMapping( value = "/register" )
    public ResponseEntity<Employees> registerMember(@Valid @RequestBody RegisterForm registerForm, Errors errors,
                                                    HttpServletRequest request ) {

        constraintViolationCheck( errors, request );

        return new ResponseEntity<Employees>( employeesService.registerMember( registerForm ), HttpStatus.OK );
    }

    @GetMapping( value = "/{id}" )
    public Employees getEmployeeById( @PathVariable int id ) {
        return this.employeesService.getEmployeeById( id );
    }

    @PostMapping( value = "/login" )
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm credentials, HttpServletRequest request ) {

        final Authentication authentication;

        Employees employees = new Employees();
        employees = null;

        try {
            employees = this.employeesService.getEmployeesByFirstName(credentials.getUsername());

            if ( employees != null ) {

                if ( employees.isIs_blocked() ) {

                    Timestamp now = new Timestamp( new DateTime().getMillis() );
                    long duration = now.getTime() - employees.getBlocked_date().getTime();
                    long timeLeft = 9999;

                    if ( duration < BLOCKED_ACCOUNT_DURATION ) {
                        timeLeft = BLOCKED_ACCOUNT_DURATION - duration;
                    }

                    else {
                        timeLeft = 0;
                        employees.setBlocked_date( null );
                        employees.setIs_blocked( false );
                        employees.setCounter( 3 );
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

            employees.setCounter( 3 );
            employeesRepository.save( employees ); // update of counter

        } catch ( AuthenticationException e ) {

            if ( employees != null ) {

                employees.setCounter( employees.getCounter() - 1 );

                if ( employees.getCounter() == 0 ) {

                    employees.setIs_blocked( true );
                    employees.setBlocked_date( new Timestamp( new DateTime().getMillis() ) );

                }

                /*errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + member.getCounter() ) );*/
                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + employees.getCounter() );
            }

            /*errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator." ) );*/
            throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator." );

        }

        employees.setCounter( 3 );
        final Employees user = (Employees) authentication.getPrincipal();
        user.setCounter( 3 );
        final String token = this.authentificationService.login( user );
        return new ResponseEntity<>( new JwtResponse( user, token, authentication.getAuthorities() ), HttpStatus.OK );
    }

    // Get a Member by its username
    @GetMapping( value = "/profile/search/{username}" )
    public Employees getEmployeeByUsername( @PathVariable String username ) {
        return employeesService.getMemberByUserName( username );
    }

    @GetMapping( "/current" )
    public ResponseEntity<Employees> getCurrentUser() {
        return new ResponseEntity<>( this.authentificationService.getCurrentUser(), HttpStatus.OK );
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
            /*errorLogServices
                    .recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.BAD_REQUEST, exceptionMessage ) );*/
            throw new ConstraintViolationException( exceptionMessage );
        }
    }

    private void generatePrivilegeErrorIf( boolean test ) {
        if ( test ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
