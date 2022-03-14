package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "logins")
//Classe à terminer (vérifier type et relation)
public class Logins
{
    @Column(name = "login_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int login_id;

    @Column( name = "employee_id" )
    private Integer employee_id;

    @Column( name = "customer_id" )
    private Integer customer_id;

}
