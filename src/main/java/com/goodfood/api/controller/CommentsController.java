package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping(value = "")
    public List<Comments> getAllComments(){
        return this.commentsService.getAllComments();
    }

  /*  @GetMapping( value = "/comments/{id}" )
    public List<Comments> getCommentsByCommentId( @PathVariable( value = "id" ) int comment_id ) {
        return this.commentsService.getAllCommentsByCommentId( id );
    }*/
}