package com.goodfood.api.exceptions.comments;

import com.goodfood.api.exceptions.categorie.CategorieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux commentaires.
 * </p>
 * @author Gaëtan T.
 */
public class CommentsNotFoundException extends ResponseStatusException
{
    public CommentsNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
