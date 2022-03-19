package com.goodfood.api.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
//Classe à terminer (vérifier type et relation)
public class Categories implements Serializable
{
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "text_description")
    private String text_description;

    @Column(name = "html_description")
    private String html_description;

    @Column(name = "image")
    private String image;

    public Categories() {
    }

    public Categories(int category_id, String category_name,
                      String text_description, String html_description, String image) {
        this.id = category_id;
        this.category_name = category_name;
        this.text_description = text_description;
        this.html_description = html_description;
        this.image = image;
    }

    public int getCategory_id() {
        return id;
    }

    public void setCategory_id(int category_id) {
        this.id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getText_description() {
        return text_description;
    }

    public void setText_description(String text_description) {
        this.text_description = text_description;
    }

    public String getHtml_description() {
        return html_description;
    }

    public void setHtml_description(String html_description) {
        this.html_description = html_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
