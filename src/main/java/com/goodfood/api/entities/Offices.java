package com.goodfood.api.entities;

import javax.persistence.*;



/**
 * <p>
 *  Class qui permet de définir l'entité Offices par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Offices est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "offices")
public class Offices
{
    /**
     * Propriété id qui représente l'id de l'office.
     *
     */
    @Column(name = "office_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété city qui représente la ville de l'office.
     *
     */
    @Column(name = "city")
    private String city;

    /**
     * Propriété phone qui représente le numéro de tel de l'office.
     *
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Propriété addressLine1 qui représente l'adress de l'office.
     *
     */
    @Column(name = "addressline1")
    private String addressLine1;

    /**
     * Propriété addressLine2 qui représente l'adress de l'office.
     *
     */
    @Column(name = "addressline2")
    private String addressLine2;

    /**
     * Propriété state qui représente le pays de l'office.
     *
     */
    @Column(name = "state")
    private String state;

    /**
     * Propriété country qui représente la région de l'office.
     *
     */
    @Column(name = "country")
    private String country;

    /**
     * Propriété postalCode qui représente le code postal de l'office.
     *
     */
    @Column(name = "postal_code")
    private String postalCode;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Offices()
    {

    }

    public Offices(String city, String phone, String addressLine1, String addressLine2, String state, String country,
                   String postalCode)
    {
        this.city = city;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
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

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)

    {
        this.country = country;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
}
