package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comments
{
    @Column(name = "comment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int comment_id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Customers customer_id;

    @Column( name = "isActif" )
    private boolean isActif;

    @Column( name = "date" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;
}
