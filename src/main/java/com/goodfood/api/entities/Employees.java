package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
//Classe à terminer (vérifier type et relation)
public class Employees
{
    @Column(name = "employee_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employee_id;

    @Column( name = "co_employee_id" )
    private int co_employee_id;

    @Column( name = "office_id" )
    private int office_id;

    @Column( name = "order_commodity" )
    private int order_commodity;

    @Column( name = "login_id" )
    private int login_id;

    @Column( name = "activated_account" )
    private boolean activated_account;

    @Column( name = "password" )
    private String password;

    @Column( name = "lastname" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String lastname;

    @Column( name = "firstname" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String firstname;

    @Column( name = "private_number" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String private_number;

    @Column( name = "email" )
    @NotNull( message = "Le champ doit avoir un email" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    @Email( message = "L'adresse email n'est pas valide" )
    private String email;

    @Column( name = "reports_to" )
    private Integer reports_to;
}
