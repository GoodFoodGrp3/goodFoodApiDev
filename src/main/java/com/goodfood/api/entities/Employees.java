package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


/**
 * <p>
 *  Class qui permet de définir l'entité Employees par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe Employees est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
/*@SQLDelete(sql = "UPDATE login SET deleted = true WHERE employee_id= ?")
@Where(clause = "deleted=false")*/
@Table(name = "employees")
public class Employees
{

    /**
     * Propriété id qui représente l'id de l'employer.
     *
     */
    @Column(name = "employee_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Propriété office_id qui représente l'id de l'office de l'employer.
     *
     */
    @ManyToOne
    @JoinColumn(name ="office_id")
    private Offices office_id;


    /**
     * Propriété lastname qui représente le nom de l'employer.
     *
     */
    @Column(name = "lastname")
    private String lastname;

    /**
     * Propriété firstname qui représente le prénom de l'employer.
     *
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Propriété private_number qui représente le numéro interne de l'employer.
     *
     */
    @Column(name = "private_number")
    private String private_number;

    /**
     * Propriété email qui représente l'email de l'employer.
     *
     */
    @Column(name = "email")
    private String email;

    /**
     * Propriété reports_to qui représente le supérieur hiérarchique de l'employer.
     *
     */
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
