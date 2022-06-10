package com.goodfood.api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * <p>
 *  Class qui permet de définir l'entité Products par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Products est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "products")
public class Products implements Serializable
{
    /**
     * Propriété id qui représente l'id du produit.
     *
     */
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété categories qui représente l'id de la catégorie auquel appartient le produit.
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Categories categories;

    /**
     * Propriété product_name qui représente le nom du produit.
     *
     */
    @Column(name = "product_name")
    private String product_name;

    /**
     * Propriété taxe qui représente l'objet Taxe.
     *
     */
    @ManyToOne
    @JoinColumn(name ="taxe_id")
    private Taxe taxe;

    /**
     * Propriété product_description qui représente la description du produit.
     *
     */
    @Column(name = "product_description")
    private String product_description;

    /**
     * Propriété quantity_in_stock qui représente la quantité en stock du produit.
     *
     */
    @Column(name = "quantity_in_stock")
    private int quantity_in_stock;

    /**
     * Propriété buy_price qui représente le prix du produit.
     *
     */
    @Column(name = "buy_price")
    private double buy_price;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Products(Categories categories, String product_name, String product_description, int quantity_in_stock,
                    double buy_price)
    {
        this.categories = categories;
        this.product_name = product_name;
        this.taxe = taxe;
        this.product_description = product_description;
        this.quantity_in_stock = quantity_in_stock;
        this.buy_price = buy_price;
    }

    public Products()
    {

    }


    // ***************
    // GETTER AND SETTER
    // ***************


    public Taxe getTaxe()
    {
        return taxe;
    }

    public void setTaxe(Taxe taxe)
    {
        this.taxe = taxe;
    }

    public int getProduct_id()
    {
        return id;
    }

    public void setProduct_id(int product_id)
    {
        this.id = product_id;
    }

    public Categories getCategories()
    {
        return categories;
    }

    public void setCategories(Categories categories)
    {
        this.categories = categories;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public String getProduct_description()
    {
        return product_description;
    }

    public void setProduct_description(String product_description)
    {
        this.product_description = product_description;
    }

    public int getQuantity_in_stock()
    {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock)
    {
        this.quantity_in_stock = quantity_in_stock;
    }

    public double getBuy_price()
    {
        return buy_price;
    }

    public void setBuy_price(double buy_price)
    {
        this.buy_price = buy_price;
    }
}
