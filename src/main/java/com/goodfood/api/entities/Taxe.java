package com.goodfood.api.entities;

import javax.persistence.*;

/**
 * <p>
 *  Class qui permet de définir l'entité taxe par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe taxe est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T. & Arthur D.
 */

@Entity
@Table(name = "taxe")
public class Taxe
{
    /**
     * Propriété id qui représente l'id de la taxe.
     *
     */
    @Column(name = "taxe_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété taxe_name qui représente la taxe.
     *
     */
    @Column(name = "taxe_name")
    private String taxe_name;

    /**
     * Propriété taxe_rate qui représente le tva.
     *
     */
    @Column(name = "taxe_rate")
    private String taxe_rate;

    // ***************
    // CONSTRUCTOR
    // ***************


    public Taxe(int id, String taxe_name, String taxe_rate)
    {
        this.id = id;
        this.taxe_name = taxe_name;
        this.taxe_rate = taxe_rate;
    }

    public Taxe() {
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

    public String getTaxe_name()
    {
        return taxe_name;
    }

    public void setTaxe_name(String taxe_name)
    {
        this.taxe_name = taxe_name;
    }

    public String getTaxe_rate()
    {
        return taxe_rate;
    }

    public void setTaxe_rate(String taxe_rate)
    {
        this.taxe_rate = taxe_rate;
    }
}
