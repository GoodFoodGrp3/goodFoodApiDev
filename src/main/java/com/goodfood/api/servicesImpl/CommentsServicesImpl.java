package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Customers;
import com.goodfood.api.repositories.CommentsRepository;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service( value = "commentsService" )
public class CommentsServicesImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Override
    public List<Comments> getAllComments() {
        return (List<Comments>) this.commentsRepository.findAll();
    }

    @Override
    public Comments getCommentById(int id) {
        return this.commentsRepository.findById(id);
    }

   /* @Override
    public Comments createComment(int id, String body) {
        final Comments comment = new Comments( authenticationService.getCurrentUser(),body, new Timestamp( System.currentTimeMillis()));

    }
*/

}
