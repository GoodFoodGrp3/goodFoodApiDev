package com.goodfood.api.request.customer;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *  Class qui permet de définir le formulaire de mise à jour des clients.
 * </p>
 * @author Gaëtan T.
 */
public class UpdateCustomerForm
{
    /**
     * Champ pseudo du formulaire.
     */
    @NotBlank(message = "Un pseudo ne peut être vide")
    private String customername;

    /**
     * Champ nom du formulaire.
     */
    @NotBlank(message = "Un nom ne peut être vide")
    private String contact_lastname;

    /**
     * Champ prénom du formulaire.
     */
    @NotBlank(message = "Un prénom ne peut être vide")
    private String contact_firstname;

    /**
     * Champ téléphone du formulaire.
     */
    @NotBlank(message = "Un numéro ne peut être vide")
    private String phone;

    /**
     * Champ addressline1 du formulaire.
     */
    @NotBlank(message = "Une addresse ne peut être vide")
    private String addressline1;

    /**
     * Champ addressline2 du formulaire.
     */
    @NotBlank(message = "Une addresse ne peut être vide")
    private String addressline2;

    /**
     * Champ ville du formulaire.
     */
    @NotBlank(message = "Une ville ne peut être vide")
    private String city;

    /**
     * Champ pays du formulaire.
     */
    @NotBlank(message = "Un état ne peut être vide")
    private String state;

    /**
     * Champ code postal du formulaire.
     */
    @NotBlank(message = "Un code postal ne peut être vide")
    private String postal_code;

    /**
     * Champ région du formulaire.
     */
    @NotBlank(message = "Un pays ne peut être vide")
    private String country;

    /**
     * Champ email du formulaire.
     */
    @NotBlank(message = "Un email ne peut être vide")
    private String email;


    // ***************
    // CONSTRUCTOR
    // ***************

    public UpdateCustomerForm(String customername, String contact_lastname, String contact_firstname,
                              String phone, String addressline1, String addressline2, String city,
                              String state, String postal_code, String country, String email) {
        this.customername = customername;
        this.contact_lastname = contact_lastname;
        this.contact_firstname = contact_firstname;
        this.phone = phone;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country = country;
        this.email = email;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public String getCustomername()
    {
        return customername;
    }

    public void setCustomername(String customername)
    {
        this.customername = customername;
    }

    public String getContact_lastname()
    {
        return contact_lastname;
    }

    public void setContact_lastname(String contact_lastname)
    {
        this.contact_lastname = contact_lastname;
    }

    public String getContact_firstname()
    {
        return contact_firstname;
    }

    public void setContact_firstname(String contact_firstname)
    {
        this.contact_firstname = contact_firstname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddressline1()
    {
        return addressline1;
    }

    public void setAddressline1(String addressline1)
    {
        this.addressline1 = addressline1;
    }

    public String getAddressline2()
    {
        return addressline2;
    }

    public void setAddressline2(String addressline2)
    {
        this.addressline2 = addressline2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPostal_code()
    {
        return postal_code;
    }

    public void setPostal_code(String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
