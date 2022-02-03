package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
//Classe à terminer (vérifier type et relation)
public class categories
{
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "text_description")
    private String text_description;

    @Column(name = "html_description")
    private String html_description;

    @Column(name = "image")
    private String image;

}
