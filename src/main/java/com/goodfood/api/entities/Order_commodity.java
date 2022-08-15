package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * <p>
 *  Class qui permet de définir l'entité Order_commodity par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Order_commodity est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "order_commodity")
public class Order_commodity
{
    /**
     * Propriété order_commodity_id qui représente l'id de la commande matière première.
     *
     */
    @Column(name = "order_commodity_id")
    @Id
    private String order_commodity_id;

    /**
     * Propriété employees qui représente l'id de l'employee qui a fais la commande.
     *
     */
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employees employees;

    /**
     * Propriété order_date qui représente la date de la commande.
     *
     */
    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp order_date;

    /**
     * Propriété order_date qui représente la date de la commande.
     *
     */
    @Column(name = "delivery_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp delivery_date;

    /**
     * Propriété shipped_date qui représente la date d'achat de la commande.
     *
     */
    @Column(name = "shipped_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp shipped_date;

    /**
     * Propriété status qui représente le status de la commande.
     *
     */
    @Column(name = "status")
    private String status;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Order_commodity()
    {

    }

    public String getOrder_commodity_id() {
        return order_commodity_id;
    }

    public void setOrder_commodity_id(String order_commodity_id) {
        this.order_commodity_id = order_commodity_id;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
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
}
