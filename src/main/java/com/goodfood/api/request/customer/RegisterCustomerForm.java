package com.goodfood.api.request.customer;

import com.goodfood.api.entities.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterCustomerForm
{
    private int order;

    private int comment;

    private int employee;

    @NotNull(message = "le mot de passe est requis")
    @Size(min = 7, message = "mot de passe trop court")
    private String password;

    @NotNull(message = "le mot de passe est requis")
    private String cpassword;

    @NotNull(message = "Un pseudo est requis")
    @NotBlank(message = "Un pseudo ne peut être vide")
    private String username;

    @NotNull(message = "Un nom est requis")
    @NotBlank(message = "Un pseudo ne peut être vide")
    private String lastname;

    @NotNull(message = "Un prénom est requis")
    @NotBlank(message = "Un pseudo ne peut être vide")
    private String firstname;

    @NotNull(message = "Un numéro est requis")
    @Size(max = 10, message = "Numéro trop long")
    private String phone;

    @NotBlank(message = "l'adresse postal ne peut être vide")
    @NotNull(message = "l'adresse postal est requise")
    private String addressline1;

    private String addressline2;

    @NotBlank(message = "la ville ne peut être vide")
    @NotNull(message = "la ville est requise")
    private String city;

    private String state;

    @NotBlank(message = "le code postal ne peut être vide")
    @NotNull(message = "le code postal est requise")
    private String postalCode;

    @NotBlank(message = "le pays ne peut être vide")
    @NotNull(message = "le pays est requis")
    private String country;

    @NotBlank(message = "l'adresse mail ne peut être vide")
    @NotNull(message = "l'adresse mail est requise")
    @Email(message = "L'adresse n'est pas valide")
    private String email;


    public RegisterCustomerForm()
    {

    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getComment()
    {
        return comment;
    }

    public void setComment(int comment)
    {
        this.comment = comment;
    }

    public int getEmployee()
    {
        return employee;
    }

    public void setEmployee(int employee)
    {
        this.employee = employee;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCpassword()
    {
        return cpassword;
    }

    public void setCpassword(String cpassword)
    {
        this.cpassword = cpassword;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
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

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
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
