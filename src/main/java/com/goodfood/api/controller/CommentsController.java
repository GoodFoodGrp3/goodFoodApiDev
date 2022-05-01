package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.services.CommentsService;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/comments")
public class CommentsController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private CustomersService customersService;


    @Autowired
    UserDetailsService userDetailsService;


    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Comments> getAll()
    {
        return this.commentsService.getAllComments();
    }

    @GetMapping( value = "/{id}" )
    public Comments getCommentById( @PathVariable int id )
    {
        return this.commentsService.getCommentById( id );
    }


    // ***************
    // POST/CREATE
    // ***************

//    @PostMapping( value = "" )
//    public Comments createComment( @RequestBody CreateCommentForm createCommentForm )
//    {
//        return this.commentsService.createComment( createCommentForm.getId(),createCommentForm.getContent());
//    }

    // ***************
    // DELETE
    // ***************

    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete(@PathVariable( value = "id" ) int id )
    {
        this.commentsService.deleteCommentById( id );
    }


    // ***************
    // UPDATE
    // ***************

    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Comments> updateComment(@PathVariable( value = "id" ) int id, String newContent )
    {
        LoginDao customerUser = customersService.getCustomerByCustomerId(id);
        LoginDao employeeUser = employeesService.getEmployeeByEmployeeId(id);

        if(customerUser.getCustomerNumber().getId() == id && customerUser.getStatus() == Status.UTILISATEUR ||
                employeeUser.getStatus()== Status.EMPLOYEE || employeeUser.getStatus()== Status.RESTAURATEUR ||
                employeeUser.getStatus()== Status.ADMINISTRATEUR)
        {
            return new ResponseEntity<>( this.commentsService.updateComment( id, newContent), HttpStatus.OK );
        }

        errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                "You have not the right authorities."));
        throw new EmployeeStatusException();
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

}