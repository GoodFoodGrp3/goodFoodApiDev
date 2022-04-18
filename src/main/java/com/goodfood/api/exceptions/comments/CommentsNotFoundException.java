package com.goodfood.api.exceptions.comments;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommentsNotFoundException extends ResponseStatusException
{
    public CommentsNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
