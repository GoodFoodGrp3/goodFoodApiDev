package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
//@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE customer_id= ?")
//@Where(clause = "deleted=false")
@Table(name = "customers")

public class Customers
{

    @Column(name = "customer_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    ///// RELATION /////
    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private Set<Orders> orders;
    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private Set<Comments> comments;

    ///// RELATION /////
    @Column(name = "contact_lastname")
    private String lastname;

    @Column(name = "contact_firstname")
    private String firstname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "addressline1")
    private String addressline1;

    @Column(name = "addressline2")
    private String addressline2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    // ***************
    // CONSTRUCTOR
    // ***************

    public Customers()
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

    public Set<Orders> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Orders> orders)
    {
        this.orders = orders;
    }

    public Set<Comments> getComments()
    {
        return comments;
    }

    public void setComments(Set<Comments> comments)
    {
        this.comments = comments;
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
