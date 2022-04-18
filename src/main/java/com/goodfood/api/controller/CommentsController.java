package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.EmployeeStatusException;
import com.goodfood.api.request.CreateCommentForm;
import com.goodfood.api.services.CommentsService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping( value = "" )
    public Comments createComment( @RequestBody CreateCommentForm createCommentForm )
    {
        return this.commentsService.createComment( createCommentForm.getId(),createCommentForm.getContent());
    }

    // ***************
    // DELETE
    // ***************

    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete( @PathVariable( value = "id" ) int id )
    {
        /*Status status = authenticationService.getCurrentUser().getStatus();
        generatePrivilegeErrorIf( status == Status.RESTAURATEUR || status == Status.ADMINISTRATEUR  );*/

        this.commentsService.deleteCommentById( id );
    }


    // ***************
    // UPDATE
    // ***************

    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Comments> updateComment(@PathVariable( value = "id" ) int id, String newContent )
    {
        return new ResponseEntity<>( this.commentsService.updateComment( id, newContent), HttpStatus.OK );
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