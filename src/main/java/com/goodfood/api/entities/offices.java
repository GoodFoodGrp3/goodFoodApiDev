package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "offices")
//Classe à terminer (vérifier type et relation)
public class offices
{
    @Column(name = "office_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int office_id;

    @Column( name = "city" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String city;

    @Column( name = "phone" )
    @NotNull( message = "Le champ doit avoir un numéro de téléphone" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String phone;

    @Column( name = "addressLine1" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine1;

    @Column( name = "addressLine2" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine2;

    @Column( name = "state" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String state;

    @Column( name = "country" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String country;

    @Column( name = "postalCode" )
    @NotNull( message = "Le champ doit avoir un code postal" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String postalCode;

    @Column( name = "territory" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String territory;
}
