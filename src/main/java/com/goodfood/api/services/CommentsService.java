package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;

import java.util.List;


public interface CommentsService {

    List<Comments> getAllComments();
    Comments getCommentById( int id );
    //Comments createComment( int id,String body );

}