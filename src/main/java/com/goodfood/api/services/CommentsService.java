package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;


public interface CommentsService {

    List<Comments> getAllComments();
    //List<Comments> getAllCommentsByCommentId( int comment_id );

}