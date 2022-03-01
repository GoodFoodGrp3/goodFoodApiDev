package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @GetMapping("/comments")
    public List<Comments> getAllComments(){
        return commentsService.getAllComments();
    }
}