package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
//Classe à terminer (vérifier type et relation)
public class products
{
    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String productline_id;

    @Column( name = "product_name" )
    private String product_name;

    @Column( name = "product_description" )
    private String product_description;

    @Column( name = "quantity_in_stock" )
    private int quantity_in_stock;

    @Column( name = "buy_price" )
    private double buy_price;

    @Column( name = "msrp" )
    private double msrp;

    @Column( name = "type" )
    private String type;

}
