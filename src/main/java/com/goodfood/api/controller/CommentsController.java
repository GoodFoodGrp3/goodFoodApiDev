package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.ErrorLog;
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

    @GetMapping(value = "")
    public List<Comments> getAll(){
        return this.commentsService.getAllComments();
    }

  /*  @GetMapping( value = "/comments/{id}" )
    public List<Comments> getCommentsByCommentId( @PathVariable( value = "id" ) int comment_id ) {
        return this.commentsService.getAllCommentsByCommentId( id );
    }*/

    // ***************
    // ERROR MANAGEMENT
    // ***************

}