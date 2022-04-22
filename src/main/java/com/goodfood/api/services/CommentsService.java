package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;

import java.util.List;


public interface CommentsService
{
    List<Comments> getAllComments() throws CommentsNotFoundException;
    Comments getCommentById(int id) throws CommentsNotFoundException;
    void deleteCommentById(int id);
    Comments updateComment(int id, String newContent);
//    Comments createComment(int id,String body);
}