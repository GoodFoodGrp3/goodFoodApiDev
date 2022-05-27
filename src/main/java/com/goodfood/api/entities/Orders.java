package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * <p>
 *  Class qui permet de définir l'entité Orders par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Orders est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "orders")
public class Orders
{
    /**
     * Propriété id qui représente l'id de la commande.
     *
     */
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété customers qui représente l'id du customer ayant fais la commande.
     *
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

    /**
     * Propriété order_date qui représente la date de la commande.
     *
     */
    @Column(name = "order_date")
    private Timestamp order_date;

    /**
     * Propriété delivery_date qui représente la date de livraison de la commande.
     *
     */
    @Column(name = "delivery_date")
    private Timestamp delivery_date;

    /**
     * Propriété delivery_date qui représente la date d'achat de la commande.
     *
     */
    @Column(name = "shipped_date")
    private Timestamp shipped_date;

    /**
     * Propriété status qui représente le status de la commande.
     *
     */
    @Column(name = "status")
    private String status;

    /**
     * Propriété comments qui représente le commentaire de la commande.
     *
     */
    @Column(name = "comments")
    private String comments;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Orders()
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

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }

    public Timestamp getOrder_date()
    {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date)
    {
        this.order_date = order_date;
    }

    public Timestamp getDelivery_date()
    {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date)
    {
        this.delivery_date = delivery_date;
    }

    public Timestamp getShipped_date()
    {
        return shipped_date;
    }

    public void setShipped_date(Timestamp shipped_date)
    {
        this.shipped_date = shipped_date;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }
}
