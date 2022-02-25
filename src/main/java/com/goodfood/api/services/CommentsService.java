package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> getAllComments() {
        return (List<Comments>) commentsRepository.findAll();
    }
}
