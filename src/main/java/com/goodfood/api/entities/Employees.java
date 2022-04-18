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
@SQLDelete(sql = "UPDATE employees SET deleted = true WHERE employee_id= ?")
@Where(clause = "deleted=false")
@Table(name = "employees")
public class Employees implements UserDetails
{
    @Column(name = "employee_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name ="office_id")
    private Offices office_id;

    @JsonIgnore
    @OneToMany(mappedBy = "employees")
    private Set<Order_commodity> order_commodity;

    @Column(name = "activated_account")
    private boolean activated_account;

    @Column(name = "password")
    private String password;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "private_number")
    private String private_number;

    @Column(name = "email")
    private String email;

    @Column(name = "reports_to")
    private Integer reports_to;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Employees()
    {
        this.activated_account = true;
        this.status = Status.RESTAURATEUR;
        this.is_blocked = false;
        this.counter = 3;
    }

    public Employees(int id, Offices office_id, Set<Order_commodity> order_commodity, boolean activated_account,
                     String password, String lastname, String firstname, String private_number, String email,
                     Integer reports_to, Status status, boolean is_blocked, int counter, Timestamp blocked_date,
                     boolean deleted, Collection<? extends GrantedAuthority> authorities)
    {
        this.id = id;
        this.office_id = office_id;
        this.order_commodity = order_commodity;
        this.activated_account = activated_account;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.private_number = private_number;
        this.email = email;
        this.reports_to = reports_to;
        this.status = status;
        this.is_blocked = is_blocked;
        this.counter = counter;
        this.blocked_date = blocked_date;
        this.deleted = deleted;
        this.authorities = authorities;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Offices getOffice_id()
    {
        return office_id;
    }

    public void setOffice_id(Offices office_id)
    {
        this.office_id = office_id;
    }

    public Set<Order_commodity> getOrder_commodity()
    {
        return order_commodity;
    }

    public void setOrder_commodity(Set<Order_commodity> order_commodity)
    {
        this.order_commodity = order_commodity;
    }

    public boolean isActivated_account()
    {
        return activated_account;
    }

    public void setActivated_account(boolean activated_account)
    {
        this.activated_account = activated_account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public String getPrivate_number()
    {
        return private_number;
    }

    public void setPrivate_number(String private_number)
    {
        this.private_number = private_number;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getReports_to()
    {
        return reports_to;
    }

    public void setReports_to(Integer reports_to)
    {
        this.reports_to = reports_to;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Override
    public String getUsername()
    {
        return firstname;
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

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }

    public boolean isIs_blocked()
    {
        return is_blocked;
    }

    public void setIs_blocked(boolean isBlocked) {
        this.is_blocked = isBlocked;
    }

    @JsonIgnore
    public int getCounter()
    {
        return counter;
    }

    @JsonIgnore
    public void setCounter( int counter)
    {
        this.counter = counter;
    }

    public Timestamp getBlocked_date()
    {
        return blocked_date;
    }

    public void setBlocked_date(Timestamp blockedDate)
    {
        this.blocked_date = blockedDate;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }
}
