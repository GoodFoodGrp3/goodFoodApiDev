package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "logins")
//Classe à terminer (vérifier type et relation)
public class logins
{
    @Column(name = "login_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int login_id;

    private int employee_id;
    private int customer_id;

    @Column( name = "password" )
    @NotNull( message = "Le champ doit contenir un mot de passe" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String password;

    @Column( name = "creation_date" )
    private Timestamp creation_date;

    @Column( name = "update_date" )
    private Timestamp update_date;

    @Column( name = "enabled" )
    private boolean enabled;

    @Column( name = "login" )
    private String login;

    @Column( name = "isRecurring" )
    private boolean isRecurring;
}
