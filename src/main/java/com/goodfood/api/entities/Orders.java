package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
//Classe à terminer (vérifier type et relation)
public class Orders
{
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customer_id;

    @Column( name = "order_date" )
    private Timestamp order_date;

    @Column( name = "delivery_date" )
    private Timestamp delivery_date;

    @Column( name = "shipped_date" )
    private Timestamp shipped_date;

    @Column( name = "status" )
    private String status;

    @Column( name = "comments" )
    private String comments;

    public Orders() {
    }

    public Orders(int id, int customer_id, Timestamp order_date, Timestamp delivery_date, Timestamp shipped_date, String status, String comments) {
        this.id = id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.shipped_date = shipped_date;
        this.status = status;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int order_id) {
        this.id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Timestamp getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(Timestamp shipped_date) {
        this.shipped_date = shipped_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
