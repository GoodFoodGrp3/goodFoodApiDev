package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;


@Entity
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE customer_id= ?")
@Where(clause = "deleted=false")
@Table(name = "customers")
public class Customers implements UserDetails
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

    @Column(name = "activated_account")
    private boolean activated_account;

    @Column(name = "password")
    private String password;

    @Column(name = "customername")
    private String customername;

    @Column(name = "contact_lastname")
    private String contact_lastname;

    @Column(name = "contact_firstname")
    private String contact_firstname;

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

    @Column(name = "is_customer_actif")
    private boolean is_customer_actif;

    @Column(name = "creation_time")
    private Timestamp creation_time;

  /*  @Column( name = "creation_time_utc" )
    private Timestamp creation_time_utc;*/

    @Column(name = "modification_time")
    private Timestamp modification_time;

    @Column(name = "delete_time")
    private Timestamp delete_time;

    @Column(name = "is_blocked")
    private boolean is_blocked;

    @Column(name = "counter")
    private int counter;

    @Column(name = "blocked_date")
    private Timestamp blocked_date;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    // ***************
    // CONSTRUCTOR
    // ***************

    public Customers()
    {
        this.activated_account = true;
        this.is_blocked = false;
        this.counter = 3;
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

    public boolean isActivated_account()
    {
        return activated_account;
    }

    public void setActivated_account(boolean activated_account)
    {
        this.activated_account = activated_account;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCustomername()
    {
        return customername;
    }

    public void setCustomername(String customer_name)
    {
        this.customername = customer_name;
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

    public boolean isIs_customer_actif()
    {
        return is_customer_actif;
    }

    public void setIs_customer_actif(boolean is_customer_actif)

    {
        this.is_customer_actif = is_customer_actif;
    }

    public Timestamp getCreation_time()
    {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time)
    {
        this.creation_time = creation_time;
    }

    public Timestamp getModification_time()
    {
        return modification_time;
    }

    public void setModification_time(Timestamp modification_time)
    {
        this.modification_time = modification_time;
    }

    public Timestamp getDelete_time()
    {
        return delete_time;
    }

    public void setDelete_time(Timestamp delete_time)
    {
        this.delete_time = delete_time;
    }

    public boolean isIs_blocked()
    {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked)
    {
        this.is_blocked = is_blocked;
    }

    public int getCounter()
    {
        return counter;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public Timestamp getBlocked_date()
    {
        return blocked_date;
    }

    public void setBlocked_date(Timestamp blocked_date)
    {
        this.blocked_date = blocked_date;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return customername;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }
}
