package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * <p>
 *  Class qui permet de définir l'entité Commodity par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Commodity est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "commodity")
public class Commodity
{


    /**
     * Propriété id qui représente l'id de la matière première.
     *
     */
    @Column(name = "commodity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    ///// RELATION /////

    /**
     * Propriété provider qui représente l'objet Provider.
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    /**
     * Propriété employees qui représente l'objet Employees.
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private Employees employees;

    /**
     * Propriété code_tva_id qui représente l'objet Taxe.
     *
     */
    @ManyToOne
    @JoinColumn(name ="taxe_id")
    private Taxe taxe;

    ///// RELATION /////

    /**
     * Propriété commodity_name qui représente le nom de la matière première.
     *
     */
    @Column(name = "commodity_name")
    @NotNull(message = "La matière première doit avoir un nom")
    @NotBlank(message = "le champ ne peut pas être vide")
    private String commodity_name;

    /**
     * Propriété commodity_description qui représente la description de la matière première.
     *
     */
    @Column(name = "commodity_description")
    @NotNull( message = "La matière première doit avoir une description")
    @NotBlank( message = "le champ ne peut pas être vide")
    private String commodity_description;

    /**
     * Propriété unit qui représente la mesure du produit.
     *
     */
    @Column( name = "unit")
    private String unit;

    /**
     * Propriété buy_price qui représente le prix d'achat de la matière première.
     *
     */
    @Column( name = "buy_price" )
    private double buy_price;

    /**
     * Propriété vendor_provider qui représente le fourisseur qui vend la matière première.
     *
     */
    @Column( name = "vendor_provider" )
    private String vendor_provider;

    /**
     * Propriété quantity qui représente la quantité de la matière première.
     *
     */
    @Column( name = "quantity" )
    private int quantity;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Commodity()
    {

    }


    public Commodity(Provider providerId, Employees employeeId, String commodityName, String commodityDescription, String unit, double buyPrice, String vendorProvider, int quantity)
    {
        this.provider = providerId;
        this.employees = employeeId;
        this.commodity_name = commodityName;
        this.commodity_description = commodityDescription;
        this.unit = unit;
        this.buy_price = buyPrice;
        this.vendor_provider=vendorProvider;
        this.quantity= quantity;
    }



    // ***************
    // GETTER AND SETTER
    // ***************

    public Provider getProvider()
    {
        return provider;
    }

    public void setProvider(Provider provider)
    {
        this.provider = provider;
    }

    public Employees getEmployees()
    {
        return employees;
    }

    public void setEmployees(Employees employees)
    {
        this.employees = employees;
    }

    public int getCommodity_id()
    {
        return id;
    }

    public void setCommodity_id(int commodity_id)
    {
        this.id = commodity_id;
    }

    public String getCommodity_name()
    {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name)
    {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_description()
    {
        return commodity_description;
    }

    public void setCommodity_description(String commodity_description)
    {
        this.commodity_description = commodity_description;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public double getBuy_price()
    {
        return buy_price;
    }

    public void setBuy_price(double buy_price)
    {
        this.buy_price = buy_price;
    }

    public String getVendor_provider()
    {
        return vendor_provider;
    }

    public void setVendor_provider(String vendor_provider)
    {
        this.vendor_provider = vendor_provider;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
