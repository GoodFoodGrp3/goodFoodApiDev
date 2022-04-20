package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "error_log" )
public class Error_log
{
    @Column(name = "error_log_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int error_log_id;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;

    @Column(name = "source")
    private String source;

    @Column(name = "error_status")
    @Enumerated( EnumType.STRING )
    private HttpStatus error_status;

    @Column(name = "message")
    private String  message;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Error_log()
    {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Error_log(String source, HttpStatus errorStatus, String message)
    {
        super();
        this.date = new Timestamp(System.currentTimeMillis());
        this.source = source;
        this.error_status = errorStatus;
        this.message = message;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getError_log_id()
    {
        return error_log_id;
    }

    public void setError_log_id(int error_log_id)
    {
        this.error_log_id = error_log_id;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public HttpStatus getErrorStatus()
    {
        return error_status;
    }

    public void setErrorStatus(HttpStatus errorStatus)
    {
        this.error_status = errorStatus;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
