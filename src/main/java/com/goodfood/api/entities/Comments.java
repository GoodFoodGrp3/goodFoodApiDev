package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

//Ajout de jsonIgnore sur la relation customers -> comments (a voir pour plus tard)
@Entity
@Table(name = "comments")
public class Comments implements Serializable {


    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @Column(name = "is_actif")
    private boolean is_actif;

    @Column( name = "date" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;

    @Column( name = "content" )
    private String content;


    public Comments(int comment_id, Customers customers, boolean is_actif, Timestamp date, String content) {
        super();
        this.id = comment_id;
        this.customers = customers;
        this.is_actif = is_actif;
        this.date = date;
        this.content = content;
    }


    //Rajouter customer quand l'authentification sera faite
    public Comments(Timestamp date, String content) {
        super();
        this.date = date;
        this.content = content;
    }


    public Comments() {
        super();
    }

    public int getComment_id() {
        return id;
    }

    public void setComment_id(int comment_id) {
        this.id = comment_id;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public boolean isIs_actif() {
        return is_actif;
    }

    public void setIs_actif(boolean is_actif) {
        this.is_actif = is_actif;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
