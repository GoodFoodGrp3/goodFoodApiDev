package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "productlines")
//Classe à terminer (vérifier type et relation)
public class productlines
{
    @Column(name = "productline_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productline_id;

    @Column(name = "text_description")
    private String text_description;

    @Column(name = "html_description")
    private String html_description;

    @Column(name = "image")
    private String image;

}
