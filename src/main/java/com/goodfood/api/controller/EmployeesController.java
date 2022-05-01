package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.EmployeesRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.employee.*;
import com.goodfood.api.services.EmployeesService;
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
@RequestMapping("/employees")
public class EmployeesController
{
    // ***************
    // CONSTANTS
    // ***************

    private static final long BLOCKED_ACCOUNT_DURATION = 30 * 60 * 1000L; // in milliseconds - 30 minutes


    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesRepository employeesRepository;

//    private AuthenticationService authentificationService;

    @Autowired
    private ErrorLogServices errorLogServices;



    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Employees> getAllEmployees()
    {

//        Status status = authentificationService.getCurrentEmployee().getStatus();
//        generatePrivilegeErrorIf(status != Status.ADMINISTRATEUR && status != Status.RESTAURATEUR);

        return this.employeesService.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public Employees getEmployeeById(@PathVariable int id)
    {
//        Employees currentEmployee = authentificationService.getCurrentEmployee();
//        generatePrivilegeErrorIf( currentEmployee.getId() != id );

        return this.employeesService.getEmployeeById( id );
    }

    @GetMapping(value = "/profile/search/{username}")
    public Employees getEmployeeByUsername( @PathVariable String username)
    {
        return employeesService.getEmployeeByUserName(username);
    }

//    @GetMapping("/current")
//    public ResponseEntity<Employees> getCurrentEmployee()
//    {
//        return new ResponseEntity<>(this.authentificationService.getCurrentEmployee(), HttpStatus.OK);
//    }


    // ***************
    // POST/REGISTER/LOGIN
    // ***************

    @PostMapping(value = "/register")
    public ResponseEntity<Employees> registerEmployee(@Valid @RequestBody RegisterEmployeeForm registerEmployeeForm)
    {
        //constraintViolationCheck( errors, request );

        return new ResponseEntity<Employees>(employeesService.registerEmployee(registerEmployeeForm), HttpStatus.OK);
    }


    @PutMapping(value = "/profile/{id}/password")
    public LoginDao updateEmployeePassword(@PathVariable int id,
                                           @RequestBody UpdateUserPasswordForm updateEmployeePasswordForm)
    {
//        Employees currentEmployee = authentificationService.getCurrentEmployee();
//        Status status = authentificationService.getCurrentEmployee().getStatus();
//
//        generatePrivilegeErrorIf(currentEmployee.getId() != id && status != Status.ADMINISTRATEUR
//                && status != Status.RESTAURATEUR);

        return employeesService.updatePassword(id, updateEmployeePasswordForm);
    }


    @PutMapping(value = "/profile/{id}")
    public Employees updateEmployeeById(@PathVariable int id, @Valid @RequestBody UpdateEmployeeForm updateEmployeeForm)
    {
        //constraintViolationCheck( errors, request );

        LoginDao user = employeesService.getEmployeeByEmployeeId(id);

        if (user.getEmployeeNumber().getId() == id)
        {
            return employeesService.updateEmployeeProfile(id, updateEmployeeForm);
        }

        errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                "You have not the right authorities."));
        throw new EmployeeStatusException();
    }


    @PutMapping( value = "/admin/{id}/status" )
    public LoginDao updateEmployeesStatus(@PathVariable int id,
                                          @RequestBody UpdateEmployeeStatusForm updateEmployeeStatusForm)
    {
//        Status status = authentificationService.getCurrentEmployee().getStatus();
        LoginDao user = employeesService.getEmployeeByEmployeeId(id);
//        generatePrivilegeErrorIf( status != Status.ADMINISTRATEUR );
        if(user.getStatus() != Status.ADMINISTRATEUR )
        {
            return employeesService.updateStatus(id, updateEmployeeStatusForm);
        }

        errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                "You have not the right authorities."));
        throw new EmployeeStatusException();

    }

    // ***************
    // DELETE
    // ***************

    @DeleteMapping(value = "/profile/{id}")
    public void deleteEmployeeById(@PathVariable int id)
    {
        LoginDao user = employeesService.getEmployeeByEmployeeId(id);

        if(user.getStatus()== Status.EMPLOYEE || user.getStatus()== Status.RESTAURATEUR ||
                user.getStatus()== Status.ADMINISTRATEUR)
        {
            this.employeesService.deleteById(id);
        }

        errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                "You have not the right authorities."));
        throw new EmployeeStatusException();
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck(Errors errors, HttpServletRequest request)
    {
        if (errors.hasErrors())
        {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();

            for (ObjectError e : errors.getAllErrors())
            {
                violationsList.add(e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";

            for (ConstraintViolation<?> violation : violationsList)
            {
                if (violationsList.indexOf(violation) > 0)
                {
                    exceptionMessage += " | ";
                }

                exceptionMessage += violation.getMessage();
            }
            errorLogServices
                    .recordLog(new ErrorLog(request.getHeader("Host"), HttpStatus.BAD_REQUEST, exceptionMessage));
            throw new ConstraintViolationException(exceptionMessage);
        }
    }

    private void generatePrivilegeErrorIf(boolean test)
    {
        if (test)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities."));
            throw new EmployeeStatusException();
        }
    }
}
