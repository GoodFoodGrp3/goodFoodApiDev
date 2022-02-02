package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
//Classe à terminer (vérifier type et relation)
public class employees
{
    @Column(name = "employee_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    private int co_employee_id;
    private int office_id;
    private int order_commodity;
    private int login_id;

    @Column( name = "lastname" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String lastname;

    @Column( name = "firstname" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String firstname;

    @Column( name = "extension" )
    @NotNull( message = "Le champ doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String extension;

    @Column( name = "email" )
    @NotNull( message = "Le champ doit avoir un email" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    @Email( message = "L'adresse email n'est pas valide" )
    private String email;

    @Column( name = "role" )
    private String role;

    @Column( name = "reports_to" )
    private int reports_to;
}
