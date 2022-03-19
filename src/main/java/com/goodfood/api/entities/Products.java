package com.goodfood.api.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products")
//Classe à terminer (vérifier type et relation)
public class Products implements Serializable
{
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Categories categories;

    @Column( name = "product_name" )
    private String product_name;

    @Column( name = "product_description" )
    private String product_description;

    @Column( name = "quantity_in_stock" )
    private int quantity_in_stock;

    @Column( name = "buy_price" )
    private double buy_price;

    public Products(int product_id, Categories categories, String product_name, String product_description, int quantity_in_stock, double buy_price) {
        this.id = product_id;
        this.categories = categories;
        this.product_name = product_name;
        this.product_description = product_description;
        this.quantity_in_stock = quantity_in_stock;
        this.buy_price = buy_price;
    }

    public Products()
    {

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

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }
}
