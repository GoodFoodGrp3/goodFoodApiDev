package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "commodity")
//Classe à terminer (vérifier type et relation)
public class Commodity
{
    @Column(name = "commodity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    ///// RELATION /////

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private Employees employees;

    ///// RELATION /////

    @Column( name = "commodity_name" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String commodity_name;

    @Column( name = "commodity_description" )
    @NotNull( message = "La resource doit avoir une description" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String commodity_description;

    @Column( name = "quantity_in_stock" )
    private int quantity_in_stock;

    @Column( name = "buy_price" )
    private double buy_price;

    @Column( name = "vendor_provider" )
    private String vendor_provider;

    @Column( name = "quantity" )
    private int quantity;

    ///// CONSTRUCTOR /////

    public Commodity() {
    }

    public Commodity(int commodity_id, String commodity_name, String commodity_description, int quantity_in_stock,
                     double buy_price, String vendor_provider, int quantity) {
        this.id = commodity_id;
        this.commodity_name = commodity_name;
        this.commodity_description = commodity_description;
        this.quantity_in_stock = quantity_in_stock;
        this.buy_price = buy_price;
        this.vendor_provider = vendor_provider;
        this.quantity = quantity;
    }

    public Commodity(String commodity_name, String commodity_description, int quantity_in_stock, double buy_price,
                     String vendor_provider, int quantity) {
        this.commodity_name = commodity_name;
        this.commodity_description = commodity_description;
        this.quantity_in_stock = quantity_in_stock;
        this.buy_price = buy_price;
        this.vendor_provider = vendor_provider;
        this.quantity = quantity;
    }

    ///// CONSTRUCTOR /////

    ///// GETTER AND SETTER /////

    public int getCommodity_id() {
        return id;
    }

    public void setCommodity_id(int commodity_id) {
        this.id = commodity_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_description() {
        return commodity_description;
    }

    public void setCommodity_description(String commodity_description) {
        this.commodity_description = commodity_description;
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

    public String getVendor_provider() {
        return vendor_provider;
    }

    public void setVendor_provider(String vendor_provider) {
        this.vendor_provider = vendor_provider;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    ///// GETTER AND SETTER /////
}
