package com.goodfood.api.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
//Classe à terminer (vérifier type et relation)
public class Customers
{
    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customer_id;

    ///// RELATION /////

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private Orders orders;

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

    @Column( name = "addressLine1" )
    @NotNull( message = "La resource doit contenir une adresse" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine1;

    @Column( name = "addressLine2" )
    @NotNull( message = "La resource doit contenir une adresse" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String addressLine2;

    @Column( name = "city" )
    @NotNull( message = "La resource doit contenir une ville" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String city;

    @Column( name = "state" )
    @NotNull( message = "La resource doit contenir un état" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String state;

    @Column( name = "postalCode" )
    @NotNull( message = "La resource doit contenir un code postal" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String postalCode;

    @Column( name = "country" )
    @NotNull( message = "La resource doit contenir un nom" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    private String country;

    @Column( name = "email" )
    @NotNull( message = "La resource doit contenir un email" )
    @NotBlank( message = "le champ ne peut pas être vide" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    @Column( name = "token" )
    private String token;

    @Column( name = "isTokenValid" )
    private boolean isTokenValid;

    @Column( name = "isTokenDelete" )
    private boolean isTokenDelete;

    @Column( name = "isCustomerActif" )
    private boolean isCustomerActif;

    ///// CONSTRUCTOR /////

    public Customers() {

    }

    public Customers(int customer_id, Orders orders, Comments comments, Logins logins, Employees employees,
                     String customer_name, String contact_lastname, String contact_firstname, String phone,
                     String addressLine1, String addressLine2, String city, String state, String postalCode,
                     String country, String email, String token, boolean isTokenValid, boolean isTokenDelete,
                     boolean isCustomerActif) {
        this.customer_id = customer_id;
        this.orders = orders;
        this.comments = comments;
        this.logins = logins;
        this.employees = employees;
        this.customer_name = customer_name;
        this.contact_lastname = contact_lastname;
        this.contact_firstname = contact_firstname;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.token = token;
        this.isTokenValid = isTokenValid;
        this.isTokenDelete = isTokenDelete;
        this.isCustomerActif = isCustomerActif;
    }

    ///// CONSTRUCTOR /////

    ///// GETTER AND SETTER /////


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isTokenValid() {
        return isTokenValid;
    }

    public void setTokenValid(boolean tokenValid) {
        isTokenValid = tokenValid;
    }

    public boolean isTokenDelete() {
        return isTokenDelete;
    }

    public void setTokenDelete(boolean tokenDelete) {
        isTokenDelete = tokenDelete;
    }

    public boolean isCustomerActif() {
        return isCustomerActif;
    }

    public void setCustomerActif(boolean customerActif) {
        isCustomerActif = customerActif;
    }

    ///// GETTER AND SETTER /////
}
