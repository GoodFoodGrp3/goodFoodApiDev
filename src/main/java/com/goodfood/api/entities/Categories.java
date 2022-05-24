package com.goodfood.api.entities;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 *  Class qui permet de définir l'entité Categories par rapport à la base de données.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@Entity</b> permet de spécifier que la classe Categories est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@Entity
@Table(name = "categories")
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


    // ***************
    // CONSTRUCTOR
    // ***************

    public Categories()
    {

    }

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
}
