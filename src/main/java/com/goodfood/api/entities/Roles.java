package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
//Classe à terminer (vérifier type et relation)
public class Roles
{
    @Column(name = "role_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;

    @Column( name = "name" )
    private String name;

    @Column( name = "authority" )
    private String authority;

}