package com.goodfood.api.entities;


import javax.persistence.*;

/**
 * <p>
 *  Class qui permet de définir l'entité Order_commodity_details par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Order_commodity_details est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T. & Arthur D.
 */

@Entity
@Table(name = "order_commodity_details")
public class Order_commodity_details
{
    /**
     * Propriété id qui représente l'id de Order_commodity_details.
     *
     */
    @Column(name = "order_commodity_details_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "commodity_id")
    private int commodity_id;

    /**
     * Propriété order_commodity qui représente l'id de order_commodity.
     *
     */
    @Column(name = "order_commodity_id")
    private String order_commodity_id;

    @Column(name = "commodity_name")
    private String commodity_name;

    @Column(name ="unit")
    private String unit;

    @Column(name ="code_tva_id")
    private int code_tva_id;

    @Column(name = "quantity_ordered")
    private int quantity_ordered;

    @Column(name = "price_each")
    private double priceEach;

    // ***************
    // CONSTRUCTOR
    // ***************

    public Order_commodity_details() {}

    public Order_commodity_details(int commodity_id, String order_commodity_id, String commodity_name,
                                   String unit, int code_tva_id, int quantity_ordered, double priceEach) {
        this.commodity_id = commodity_id;
        this.order_commodity_id = order_commodity_id;
        this.commodity_name = commodity_name;
        this.unit = unit;
        this.code_tva_id = code_tva_id;
        this.quantity_ordered = quantity_ordered;
        this.priceEach = priceEach;
    }

    // ***************
    // GETTER AND SETTER
    // ***************


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getOrder_commodity_id() {
        return order_commodity_id;
    }

    public void setOrder_commodity_id(String order_commodity_id) {
        this.order_commodity_id = order_commodity_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCode_tva_id() {
        return code_tva_id;
    }

    public void setCode_tva_id(int code_tva_id) {
        this.code_tva_id = code_tva_id;
    }

    public int getQuantity_ordered() {
        return quantity_ordered;
    }

    public void setQuantity_ordered(int quantity_ordered) {
        this.quantity_ordered = quantity_ordered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }
}
