package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Ajout de jsonIgnore sur la relation comments -> customers (a voir pour plus tard)
@Entity
@Table(name = "customers")
//Classe à terminer (vérifier type et relation)
public class Customers
{
    @Column(name = "customer_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    ///// RELATION /////

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comments comments;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "login_id")
    private Logins logins;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private Employees employees;

    ///// RELATION /////

    @Column( name = "activated_account" )
    private boolean activated_account;

    @Column( name = "password" )
    private String password;

    @Column( name = "customer_name" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String customer_name;

    @Column( name = "contact_lastname" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String contact_lastname;

    @Column( name = "contact_firstname" )
    @NotNull( message = "La resource doit avoir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String contact_firstname;

    @Column( name = "phone" )
    @NotNull( message = "La resource doit contenir un numéro" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String phone;

    @Column( name = "addressline1" )
    private String addressline1;

    @Column( name = "addressline2" )
    private String addressline2;

    @Column( name = "city" )
    @NotNull( message = "La resource doit contenir une ville" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String city;

    @Column( name = "state" )
    @NotNull( message = "La resource doit contenir un état" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String state;

    @Column( name = "postal_code" )
    @NotNull( message = "La resource doit contenir un code postal" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String postal_code;

    @Column( name = "country" )
    @NotNull( message = "La resource doit contenir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String country;

    @Column( name = "email" )
    @NotNull( message = "La resource doit contenir un email" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    @Column( name = "is_customer_actif" )
    private boolean is_customer_actif;

    @Column( name = "creation_time" )
    private Timestamp creation_time;

  /*  @Column( name = "creation_time_utc" )
    private Timestamp creation_time_utc;*/

    @Column( name = "modification_time" )
    private Timestamp modification_time;

    @Column( name = "delete_time" )
    private Timestamp delete_time;

    ///// CONSTRUCTOR /////

    public Customers() {

    }

    public Customers(int customer_id, Orders orders, Comments comments, Logins logins, Employees employees,
                     boolean activated_account, String password, String customer_name, String contact_lastname,
                     String contact_firstname, String phone, String addressline1, String addressline2, String city,
                     String state, String postal_code, String country, String email, boolean is_customer_actif,
                     Timestamp creation_time, Timestamp modification_time, Timestamp delete_time) {
        this.id = customer_id;
        this.orders = orders;
        this.comments = comments;
        this.logins = logins;
        this.employees = employees;
        this.activated_account = activated_account;
        this.password = password;
        this.customer_name = customer_name;
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
        this.is_customer_actif = is_customer_actif;
        this.creation_time = creation_time;
        this.modification_time = modification_time;
        this.delete_time = delete_time;
    }

    ///// CONSTRUCTOR /////

    ///// GETTER AND SETTER /////

    public int getCustomer_id() {
        return id;
    }

    public void setCustomer_id(int customer_id) {
        this.id = customer_id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Logins getLogins() {
        return logins;
    }

    public void setLogins(Logins logins) {
        this.logins = logins;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public boolean isActivated_account() {
        return activated_account;
    }

    public void setActivated_account(boolean activated_account) {
        this.activated_account = activated_account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getContact_lastname() {
        return contact_lastname;
    }

    public void setContact_lastname(String contact_lastname) {
        this.contact_lastname = contact_lastname;
    }

    public String getContact_firstname() {
        return contact_firstname;
    }

    public void setContact_firstname(String contact_firstname) {
        this.contact_firstname = contact_firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_customer_actif() {
        return is_customer_actif;
    }

    public void setIs_customer_actif(boolean is_customer_actif) {
        this.is_customer_actif = is_customer_actif;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getModification_time() {
        return modification_time;
    }

    public void setModification_time(Timestamp modification_time) {
        this.modification_time = modification_time;
    }

    public Timestamp getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Timestamp delete_time) {
        this.delete_time = delete_time;
    }


    ///// GETTER AND SETTER /////
}
