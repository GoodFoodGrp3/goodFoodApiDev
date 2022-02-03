package com.goodfood.api.entities;

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

    private int customer_id;

    @Column( name = "isActif" )
    private boolean isActif;

    @Column( name = "date" )
    private Timestamp date;
}
