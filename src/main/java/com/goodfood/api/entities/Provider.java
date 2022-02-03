package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
//Classe à terminer (vérifier type et relation)
public class Provider
{
    @Column(name = "provider_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int provider_id;

    @Column( name = "provider_name" )
    private String provider_name;

    @Column( name = "addressLine" )
    private String addressLine;

    @Column( name = "email" )
    private String email;

    @Column( name = "phone" )
    private String phone;

    @Column( name = "country" )
    private String country;

    @Column( name = "postalCode" )
    private String postalCode;

    @Column( name = "state" )
    private String state;


}
