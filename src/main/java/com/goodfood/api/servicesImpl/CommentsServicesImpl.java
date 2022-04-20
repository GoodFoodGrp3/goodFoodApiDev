package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Error_log;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
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

@Service(value = "commentsService")
public class CommentsServicesImpl implements CommentsService
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    ErrorLogServices errorLogServices;

    @Autowired
    private AuthenticationService authenticationService;

    // ***************
    // GET
    // ***************

    @Override
    public List<Comments> getAllComments() throws CommentsNotFoundException
    {
        List<Comments> getAllComments = (List<Comments>) commentsRepository.findAll();

        if (getAllComments == null || getAllComments.isEmpty())
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "Aucun commentaires trouvé"));
            throw new CommentsNotFoundException( "Aucun commentaires trouvé" );
        }

        return getAllComments;
    }

    @Override
    public Comments getCommentById(int id) throws CommentsNotFoundException
    {
        Comments comments = commentsRepository.findById(id);

        if(comments == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "Le commentaire n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "Le commentaire n° " + id + " est introuvable");
        }

        else
        {
            return comments;
        }
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteCommentById(int id)
    {
        Comments comment = this.commentsRepository.findById(id);

        if (comment == null)
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id));
        }

        this.commentsRepository.deleteById(id);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Comments updateComment(int id, String newContent)
    {
        Comments comment = this.commentsRepository.findById(id);

        if (comment == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND,
                    String.format("None Comment could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format("None Comment could be found with the id %d", id));
        }
        comment.setContent(newContent);
        comment.setDate(new Timestamp(System.currentTimeMillis()));
        commentsRepository.save(comment);

        return comment;
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Comments createComment(int id, String body) throws CommentsNotFoundException
    {
        final Comments comment = new Comments(authenticationService.getCurrentCustomer(), body,
                new Timestamp( System.currentTimeMillis()));

        return this.commentsRepository.save(comment);
    }
}
