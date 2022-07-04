package com.goodfood.api.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 *  Class qui permet de définir l'entité Categories par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Categories est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET is_actif = false WHERE category_id=?")
@Where(clause="is_actif=1")
public class Categories implements Serializable
{

    /**
     * Propriété id qui représente l'id de la catégorie.
     *
     */
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété category_name qui représente le nom de la catégorie.
     */
    @Column(name = "category_name")
    private String category_name;

    /**
     * Propriété text_description qui représente la description de la catégorie.
     */
    @Column(name = "text_description")
    private String text_description;

    /**
     * Propriété html_description qui représente la description html de la catégorie.
     */
    @Column(name = "html_description")
    private String html_description;

    /**
     * Propriété image qui représente l'image de la catégorie.
     */
    @Column(name = "image")
    private String image;

    /**
     * Propriété is_actif qui représente si la categories est actif.
     *
     */
    @Column(name = "is_actif")
    private boolean is_actif = Boolean.TRUE;

    // ***************
    // CONSTRUCTOR
    // ***************

    public Categories()
    {

    }

    /**
     * Constructeur qui prend 4 paramètres.
     * @param categoryName de la categorie.
     * @param textDescription de la categorie.
     * @param htmlDescription de la categorie.
     * @param image de la categorie.
     */
    public Categories(String categoryName, String textDescription, String htmlDescription, String image)
    {
        this.category_name = categoryName;
        this.text_description=textDescription;
        this.html_description = htmlDescription;
        this.image = image;
    }

    public Categories(int category_id, String category_name,
                      String text_description, String html_description, String image)
    {
        this.id = category_id;
        this.category_name = category_name;
        this.text_description = text_description;
        this.html_description = html_description;
        this.image = image;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getCategory_id()
    {
        return id;
    }

    public void setCategory_id(int category_id)
    {
        this.id = category_id;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    public String getText_description()
    {
        return text_description;
    }

    public void setText_description(String text_description)
    {
        this.text_description = text_description;
    }

    public String getHtml_description()
    {
        return html_description;
    }

    public void setHtml_description(String html_description)
    {
        this.html_description = html_description;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public boolean isIs_actif() {
        return is_actif;
    }

    public void setIs_actif(boolean is_actif) {
        this.is_actif = is_actif;
    }
}
