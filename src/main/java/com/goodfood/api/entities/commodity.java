package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "commodity")
//Classe à terminer (vérifier type et relation)
public class commodity
{
    @Column(name = "commodity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commodity_id;

    //private Long provider_id;
    //private Long employee_id;

    @Column( name = "commodity_name" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String commodity_name;

    @Column( name = "commodity_description" )
    @NotNull( message = "La resource doit avoir une description" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String commodity_description;

    @Column( name = "quantity_in_stock" )
    private int quantity_in_stock;

    @Column( name = "buy_price" )
    private double buy_price;

    @Column( name = "vendor_provider" )
    private String vendor_provider;

    @Column( name = "quantity" )
    private int quantity;
}
