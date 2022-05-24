package com.goodfood.api.entities;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * <p>
 *  Class qui permet de définir l'entité Order_commodity par rapport à la base de données.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@Entity</b> permet de spécifier que la classe Order_commodity est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "order_commodity")
public class Order_commodity
{
    @Column(name = "order_commodity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_commodity_id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employees employees;

    @Column(name = "order_date")
    private Timestamp order_date;

    @Column(name = "delivery_date")
    private Timestamp delivery_date;

    @Column(name = "shipped_date")
    private Timestamp shipped_date;

    @Column(name = "status")
    private String status;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Order_commodity()
    {

    }

    public int getOrder_commodity_id()
    {
        return order_commodity_id;
    }

    public void setOrder_commodity_id(int order_commodity_id)
    {
        this.order_commodity_id = order_commodity_id;
    }

    public Employees getEmployees()
    {
        return employees;
    }

    public void setEmployees(Employees employees)
    {
        this.employees = employees;
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

    public void setRequired_date(Timestamp delivery_date)
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
}
