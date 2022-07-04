package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * <p>
 *  Class qui permet de définir l'entité Comments par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Comments est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comments SET is_actif = false WHERE comment_id=?")
@Where(clause="is_actif=1")
public class Comments implements Serializable
{

    /**
     * Propriété id qui représente l'id du comment.
     *
     */
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété customers qui représente l(id du customer.
     *
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;


    /**
     * Propriété is_actif qui représente si le commentaire est actif.
     *
     */
    @Column(name = "is_actif")
    private boolean is_actif = Boolean.TRUE;

    /**
     * Propriété date qui représente la date de création du commentaire.
     *
     */
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;

    @Column(name = "content")
    private String content;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Comments(Customers customers,  String content, Timestamp date)
    {
        super();
        this.customers = customers;
        this.content = content;
        this.date = date;
    }


    public Comments()
    {
        super();
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getComment_id()
    {
        return id;
    }

    public void setComment_id(int comment_id)
    {
        this.id = comment_id;
    }

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }

    public boolean isIs_actif()
    {
        return is_actif;
    }

    public void setIs_actif(boolean is_actif)
    {
        this.is_actif = is_actif;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
