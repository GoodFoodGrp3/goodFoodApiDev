package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.repositories.CommentsRepository;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CommentsService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service( value = "commentsService" )
public class CommentsServicesImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ErrorLogServices errorLogServices;

    @Override
    public List<Comments> getAllComments() {
        return (List<Comments>) this.commentsRepository.findAll();
    }

    @Override
    public Comments getCommentById(int id) {
        return this.commentsRepository.findById(id);
    }

    @Override
    public Comments updateComment(int id, String newContent) {
        Comments comment = this.commentsRepository.findById( id );
        if ( comment == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) );
        }
        comment.setContent( newContent );
        comment.setDate( new Timestamp( System.currentTimeMillis() ) );
        commentsRepository.save( comment );

        return comment;
    }

   /* @Override
    public Comments createComment(int id, String body) {
        final Comments comment = new Comments( authenticationService.getCurrentUser(),body, new Timestamp( System.currentTimeMillis()));

    }
*/

}
