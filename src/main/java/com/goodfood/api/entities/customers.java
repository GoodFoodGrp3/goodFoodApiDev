package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
//Classe à terminer (vérifier type et relation)
public class customers
{
    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    private Long order_id;
    private Long comment_id;
    private Long login_id;
    private Long employee_id;

    @Column( name = "customer_name" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String customer_name;

    @Column( name = "contact_lastname" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String contact_lastname;

    @Column( name = "contact_firstname" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String contact_firstname;

    @Column( name = "phone" )
    @NotNull( message = "La resource doit contenir un numéro" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String phone;

    @Column( name = "addressLine1" )
    @NotNull( message = "La resource doit contenir une adresse" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine1;

    @Column( name = "addressLine2" )
    @NotNull( message = "La resource doit contenir une adresse" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine2;

    @Column( name = "city" )
    @NotNull( message = "La resource doit contenir une ville" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String city;

    @Column( name = "state" )
    @NotNull( message = "La resource doit contenir un état" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String state;

    @Column( name = "postalCode" )
    @NotNull( message = "La resource doit contenir un code postal" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String postalCode;

    @Column( name = "country" )
    @NotNull( message = "La resource doit contenir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String country;

    @Column( name = "email" )
    @NotNull( message = "La resource doit contenir un email" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    @Column( name = "token" )
    private String token;

    @Column( name = "isTokenValid" )
    private boolean isTokenValid;

    @Column( name = "isTokenDelete" )
    private boolean isTokenDelete;

    @Column( name = "isCustomerActif" )
    private boolean isCustomerActif;



}
