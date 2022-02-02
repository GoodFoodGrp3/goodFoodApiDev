package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
//Classe à terminer (vérifier type et relation)
public class orders
{
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private int customer_id;

    @Column( name = "order_date" )
    private Timestamp order_date;

    @Column( name = "required_date" )
    private Timestamp required_date;

    @Column( name = "shipped_date" )
    private Timestamp shipped_date;

    @Column( name = "status" )
    private String status;

    @Column( name = "comments" )
    private String comments;

}
