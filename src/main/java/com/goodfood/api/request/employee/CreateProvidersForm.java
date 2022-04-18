package com.goodfood.api.request.employee;

public class CreateProvidersForm
{
    private int id;
    private String provider_name;
    private String addressline;
    private String email;
    private String phone;
    private String country;
    private String postal_code;
    private String state;


    // ***************
    // CONSTRUCTOR
    // ***************

    public CreateProvidersForm(int id, String provider_name, String addressline, String email, String phone,
                               String country, String postal_code, String state)
    {
        this.id = id;
        this.provider_name = provider_name;
        this.addressline = addressline;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.postal_code = postal_code;
        this.state = state;
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

    public String getProvider_name()
    {
        return provider_name;
    }

    public void setProvider_name(String provider_name)
    {
        this.provider_name = provider_name;
    }

    public String getAddressline()
    {
        return addressline;
    }

    public void setAddressline(String addressline)
    {
        this.addressline = addressline;
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

    public String getPostal_code()
    {
        return postal_code;
    }

    public void setPostal_code(String postal_code)
    {
        this.postal_code = postal_code;
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
