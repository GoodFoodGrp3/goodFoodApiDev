package com.goodfood.api.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <p>
 *  Class qui permet de définir l'entité order_details par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe order_details est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T. & Arthur D.
 */

@Entity
@Table(name = "order_details")
public class Order_details
{
    /**
     * Propriété id qui représente l'id de order_details.
     *
     */
    @Column(name = "order_details_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int product_id;

    /**
     * Propriété orders qui représente l'id de l'order.
     *
     */
    @Column(name = "order_id")
    private String order_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "code_tva_id")
    private int code_tva_id;

    @Column(name = "quantity_ordered")
    private int quantity_ordered;

    @Column(name = "price_each")
    private double priceEach;

    @Column(name = "order_line_number")
    private int order_line_number;


    // ***************
    // CONSTRUCTOR
    // ***************


    public Order_details(int id, int product_id, String order_id, String product_name,
                         int code_tva_id, int quantity_ordered, double priceEach, int order_line_number)
    {
        this.id = id;
        this.product_id = product_id;
        this.order_id = order_id;
        this.product_name = product_name;
        this.code_tva_id = code_tva_id;
        this.quantity_ordered = quantity_ordered;
        this.priceEach = priceEach;
        this.order_line_number = order_line_number;
    }

    public Order_details(int product_id, String order_id, String product_name, int code_tva_id,
                         int quantity_ordered, double priceEach, int order_line_number) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.product_name = product_name;
        this.code_tva_id = code_tva_id;
        this.quantity_ordered = quantity_ordered;
        this.priceEach = priceEach;
        this.order_line_number = order_line_number;
    }

    public Order_details()
    {

    }

    // ***************
    // GETTER AND SETTER
    // ***************


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(int product_id)
    {
        this.product_id = product_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public int getCode_tva_id()
    {
        return code_tva_id;
    }

    public void setCode_tva_id(int code_tva_id)
    {
        this.code_tva_id = code_tva_id;
    }

    public int getQuantity_ordered()
    {
        return quantity_ordered;
    }

    public void setQuantity_ordered(int quantity_ordered)
    {
        this.quantity_ordered = quantity_ordered;
    }

    public double getPriceEach()
    {
        return priceEach;
    }

    public void setPriceEach(double priceEach)
    {
        this.priceEach = priceEach;
    }

    public int getOrder_line_number()
    {
        return order_line_number;
    }

    public void setOrder_line_number(int order_line_number)
    {
        this.order_line_number = order_line_number;
    }
}
