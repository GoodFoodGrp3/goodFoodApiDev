package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
//Classe à terminer (vérifier type et relation)
public class Payments
{
    @Column(name = "payment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payment_id;

    private int customer_id;

    @Column( name = "payment_date" )
    private Timestamp payment_date;

    @Column( name = "amount" )
    private double amount;

}
