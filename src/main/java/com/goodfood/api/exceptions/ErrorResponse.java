package com.goodfood.api.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;
import org.joda.time.DateTime;


/**
 * <p>
 *  Class qui permet de customiser des exceptions.
 * </p>
 * @author Gaëtan T.
 */
public class ErrorResponse
{
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Paris" )
    private Timestamp date;

    private HttpStatus status;

    // General error message about nature of error
    private String message;

    // Specific errors in API request processing
    private List<String> details;


    // ***************
    // CONSTRUCTOR
    // ***************

    public ErrorResponse( HttpStatus status, String message, List<String> details )
    {
        super();
        this.date = new Timestamp( new DateTime().getMillis() );
        this.status = status;
        this.message = message;
        this.details = details;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate( Timestamp date )
    {
        this.date = date;
    }

    public HttpStatus getStatus()
    {
        return status;
    }

    public void setStatus( HttpStatus status )
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public List<String> getDetails()
    {
        return details;
    }

    public void setDetails( List<String> details )
    {
        this.details = details;
    }
}

