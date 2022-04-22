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
/*@SQLDelete(sql = "UPDATE login SET deleted = true WHERE employee_id= ?")
@Where(clause = "deleted=false")*/
@Table(name = "employees")
public class Employees
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


    // ***************
    // CONSTRUCTOR
    // ***************

    public Employees()
    {
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
}
