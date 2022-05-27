package com.goodfood.api.entities;

import javax.persistence.*;


/**
 * <p>
 *  Class qui permet de définir l'entité Provider par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Provider est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "provider")
public class Provider
{
    /**
     * Propriété id qui représente l'id du fournisseur.
     *
     */
    @Column(name = "provider_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété provider_name qui représente le nom du fournisseur.
     *
     */
    @Column(name = "provider_name")
    private String provider_name;

    /**
     * Propriété addressLine qui représente l'address du fournisseur.
     *
     */
    @Column(name = "addressline")
    private String addressLine;

    /**
     * Propriété email qui représente l'email du fournisseur.
     *
     */
    @Column(name = "email")
    private String email;

    /**
     * Propriété phone qui représente le téléphone du fournisseur.
     *
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Propriété country qui représente la région du fournisseur.
     *
     */
    @Column(name = "country")
    private String country;

    /**
     * Propriété postalCode qui représente le code postal du fournisseur.
     *
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * Propriété state qui représente le pays du fournisseur.
     *
     */
    @Column(name = "state")
    private String state;


    // ***************
    // CONSTRUCTOR
    // ***************

    public Provider()
    {

    }


    public Provider(String provider_name, String addressLine, String email, String phone, String country,
                    String postalCode, String state)
    {
        this.provider_name = provider_name;
        this.addressLine = addressLine;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.postalCode = postalCode;
        this.state = state;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getProvider_id()
    {
        return id;
    }

    public void setProvider_id(int provider_id)
    {
        this.id = provider_id;
    }

    public String getProvider_name()
    {
        return provider_name;
    }

    public void setProvider_name(String provider_name)
    {
        this.provider_name = provider_name;
    }

    public String getAddressLine()
    {
        return addressLine;
    }

    public void setAddressLine(String addressLine)
    {
        this.addressLine = addressLine;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
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

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}
