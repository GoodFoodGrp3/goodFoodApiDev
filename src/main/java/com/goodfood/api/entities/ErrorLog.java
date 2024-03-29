package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <p>
 *  Class qui permet de définir l'entité ErrorLog par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe ErrorLog est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table( name = "error_log" )
public class ErrorLog
{

    /**
     * Propriété error_log_id qui représente l'id de l'erreur.
     *
     */
    @Column(name = "error_log_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int error_log_id;

    /**
     * Propriété date qui représente la date de l'erreur.
     *
     */
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;

    /**
     * Propriété source qui représente la source de l'erreur.
     *
     */
    @Column(name = "source")
    private String source;

    /**
     * Propriété error_status qui représente le status de l'erreur.
     *
     */
    @Column(name = "error_status")
    @Enumerated( EnumType.STRING )
    private HttpStatus error_status;

    /**
     * Propriété message qui représente le message de l'erreur.
     *
     */
    @Column(name = "message")
    private String  message;


    // ***************
    // CONSTRUCTOR
    // ***************

    public ErrorLog()
    {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public ErrorLog(String source, HttpStatus errorStatus, String message)
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
