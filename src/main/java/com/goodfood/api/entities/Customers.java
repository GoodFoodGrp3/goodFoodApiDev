package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


/**
 * <p>
 *  Class qui permet de définir l'entité Customers par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Customers est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
//@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE customer_id= ?")
//@Where(clause = "deleted=false")
@Table(name = "customers")

public class Customers
{

    /**
     * Propriété id qui représente l'id du client.
     *
     */
    @Column(name = "customer_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    ///// RELATION /////

    /**
     * Propriété orders qui représente l'id de la commande client.
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private Set<Orders> orders;

    /**
     * Propriété comments qui représente l'id du commentaire client.
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "customers")
    private Set<Comments> comments;

    ///// RELATION /////

    /**
     * Propriété lastname qui représente le nom du client.
     *
     */
    @Column(name = "contact_lastname")
    private String lastname;

    /**
     * Propriété firstname qui représente le prénom du client.
     *
     */
    @Column(name = "contact_firstname")
    private String firstname;

    /**
     * Propriété phone qui représente le numéro de téléphone du client.
     *
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Propriété addressline1 qui représente l'addresse du client.
     *
     */
    @Column(name = "addressline1")
    private String addressline1;

    /**
     * Propriété addressline2 qui représente l'addresse du client.
     *
     */
    @Column(name = "addressline2")
    private String addressline2;

    /**
     * Propriété city qui représente la ville du client.
     *
     */
    @Column(name = "city")
    private String city;

    /**
     * Propriété state qui représente le pays du client.
     *
     */
    @Column(name = "state")
    private String state;

    /**
     * Propriété postal_code qui représente le code postal du client.
     *
     */
    @Column(name = "postal_code")
    private String postal_code;

    /**
     * Propriété country qui représente la région du client.
     *
     */
    @Column(name = "country")
    private String country;

    /**
     * Propriété email qui représente l'email du client.
     *
     */
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
