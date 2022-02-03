package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_commodity")
//Classe à terminer (vérifier type et relation)
public class order_commodity
{
    @Column(name = "order_commodity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_commodity_id;

    private int employee_id;

    @Column( name = "order_date" )
    private Timestamp order_date;

    @Column( name = "required_date" )
    private Timestamp required_date;

    @Column( name = "shipped_date" )
    private Timestamp shipped_date;

    @Column( name = "status" )
    private String status;


}
