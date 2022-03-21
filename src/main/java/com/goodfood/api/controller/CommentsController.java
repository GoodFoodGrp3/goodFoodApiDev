package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.EmployeeStatusException;
import com.goodfood.api.request.CreateCommentForm;
import com.goodfood.api.services.CommentsService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ErrorLogServices errorLogServices;

    @GetMapping(value = "")
    public List<Comments> getAll(){
        return this.commentsService.getAllComments();
    }

    @GetMapping( value = "/{id}" )
    public Comments getCommentById( @PathVariable int id ) {
        return this.commentsService.getCommentById( id );
    }

    /*@PostMapping( value = "" )
    public Comments createComment( @RequestBody CreateCommentForm createCommentForm ) {
        return this.commentsService.createComment( createCommentForm.getContent());
    }*/

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test ) {
        if ( test ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }

}