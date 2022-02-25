package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comments implements Serializable {

    @Column(name = "comment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int comment_id;

    @OneToOne(mappedBy = "comments")
    private Customers customers;

    @Column( name = "isActif" )
    private boolean isActif;

    @Column( name = "date" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;

    @Column( name = "content" )
    private String content;

    public Comments(int comment_id, Customers customers, boolean isActif, Timestamp date) {
        super();
        this.comment_id = comment_id;
        this.customers = customers;
        this.isActif = isActif;
        this.date = date;
    }

    public Comments() {
        super();
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public Customers getCustomer_id() {
        return customers;
    }

    public void setCustomer_id(Customers customers) {
        this.customers = customers;
    }

    public boolean isActif() {
        return isActif;
    }

    public void setActif(boolean actif) {
        isActif = actif;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
