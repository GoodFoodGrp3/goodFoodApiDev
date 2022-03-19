package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
//Classe à terminer (vérifier type et relation)
public class Employees
{
    @Column(name = "employee_id")
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column( name = "co_employee_id" )
    private int co_employee_id;


    @Column( name = "office_id" )
    private int office_id;


    @Column( name = "order_commodity" )
    private int order_commodity;


    @Column( name = "login_id" )
    private int login_id;


    @Column( name = "activated_account" )
    private boolean activated_account;


    @Column( name = "password" )
    private String password;

    @Column( name = "lastname" )
    private String lastname;

    @Column( name = "firstname" )
    private String firstname;

    @Column( name = "private_number" )
    private String private_number;

    @Column( name = "email" )
    private String email;

    @Column( name = "reports_to" )
    private Integer reports_to;

    public Employees() {
    }

    public Employees(int id, int co_employee_id, int office_id, int order_commodity, int login_id, boolean activated_account, String password, String lastname, String firstname, String private_number, String email, Integer reports_to) {
        this.id = id;
        this.co_employee_id = co_employee_id;
        this.office_id = office_id;
        this.order_commodity = order_commodity;
        this.login_id = login_id;
        this.activated_account = activated_account;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.private_number = private_number;
        this.email = email;
        this.reports_to = reports_to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCo_employee_id() {
        return co_employee_id;
    }

    public void setCo_employee_id(int co_employee_id) {
        this.co_employee_id = co_employee_id;
    }

    public int getOffice_id() {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }

    public int getOrder_commodity() {
        return order_commodity;
    }

    public void setOrder_commodity(int order_commodity) {
        this.order_commodity = order_commodity;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPrivate_number() {
        return private_number;
    }

    public void setPrivate_number(String private_number) {
        this.private_number = private_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getReports_to() {
        return reports_to;
    }

    public void setReports_to(Integer reports_to) {
        this.reports_to = reports_to;
    }
}
