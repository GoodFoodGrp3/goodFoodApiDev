package com.goodfood.api.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


/**
 * <p>
 *  Class qui permet de définir l'entité LoginDao par rapport à la base de données.
 * </p>
 * <p><b>@Entity</b> permet de spécifier que la classe LoginDao est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
//@SQLDelete(sql = "UPDATE login SET deleted = true WHERE employee_id= ?")
//@Where(clause = "deleted=false")
@Table(name="login")
public class LoginDao implements UserDetails
{
    /**
     * Propriété login_id qui représente l'id de la personne logger.
     *
     */
    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long login_id;

    /**
     * Propriété customerNumber qui représente l'id du client.
     *
     */
    @OneToOne(targetEntity = Customers.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customers customerNumber;

    /**
     * Propriété employeeNumber qui représente l'id de l'employer.
     *
     */
    @OneToOne(targetEntity = Employees.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employees employeeNumber;

    /**
     * Propriété login qui représente le login de l'employer/du customer.
     *
     */
    @Column(name = "login")
    private String login;

    /**
     * Propriété password qui représente le mot de passe de l'employer/du customer.
     *
     */
    @Column(name = "password")
    private String password;

    /**
     * Propriété status qui représente le status de l'employer/du customer.
     *
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Propriété is_blocked qui représente le compte bloqué ou non de l'employer/du customer.
     *
     */
    @Column(name = "is_blocked")
    private boolean is_blocked;

    /**
     * Propriété blocked_date qui représente la date de blocage du compte de l'employer/du customer.
     *
     */
    @Column(name = "blocked_date")
    private Timestamp blocked_date;

    /**
     * Propriété counter qui représente la nombre de tentatives de connexion avant le blocage de l'employer/du customer.
     *
     */
    @Column(name = "counter")
    private int counter;

    /**
     * Propriété deleted qui représente si le compte a été supprimé de l'employer/du customer.
     *
     */
    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    /**
     * Propriété activated_account qui représente si le compte est activer de l'employer/du customer.
     *
     */
    @Column(name = "activated_account")
    private boolean activated_account;

    /**
     * Propriété creation_time qui représente la date de création du compte de l'employer/du customer.
     *
     */
    @Column(name = "creation_time")
    private Timestamp creation_time;

  /*  @Column( name = "creation_time_utc" )
    private Timestamp creation_time_utc;*/

    /**
     * Propriété modification_time qui représente la date de modification du compte de l'employer/du customer.
     *
     */
    @Column(name = "modification_time")
    private Timestamp modification_time;

    /**
     * Propriété delete_time qui représente la date de suprpession du compte de l'employer/du customer.
     *
     */
    @Column(name = "delete_time")
    private Timestamp delete_time;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;


    public LoginDao() {
        this.activated_account = true;
        this.is_blocked = false;
        this.counter = 3;
        this.creation_time = new Timestamp(System.currentTimeMillis());
    }
//
//    public LoginEntity(Long login_id, Customers customerNumber, Employees employeeNumber, String login, String password) {
//        this.login_id = login_id;
//        this.customerNumber = customerNumber;
//        this.employeeNumber = employeeNumber;
//        this.login = login;
//        this.password = password;
//    }
//
    public LoginDao(String login, String password) {
        this.login = login;
        this.password = password;
    }
//
//    public LoginEntity(Employees employeeNumber, String login, String password) {
//        this.employeeNumber = employeeNumber;
//        this.login = login;
//        this.password = password;
//    }
//
//    public LoginEntity(LoginEntity user) {
//        this.login_id = user.getLogin_id();
//        this.customerNumber = user.getCustomerNumber();
//        this.employeeNumber = user.getEmployeeNumber();
//        this.login = user.getLogin();
//        this.password = user.getPassword();
//    }

    public LoginDao(Long login_id, Customers customerNumber, Employees employeeNumber, String login, String password, Status status,
                    boolean is_blocked, Timestamp blocked_date, int counter, boolean deleted, boolean activated_account,
                    Timestamp creation_time, Timestamp modification_time, Timestamp delete_time,
                    Collection<? extends GrantedAuthority> authorities) {
        this.login_id = login_id;
        this.customerNumber = customerNumber;
        this.employeeNumber = employeeNumber;
        this.login = login;
        this.password = password;
        this.status = status;
        this.is_blocked = is_blocked;
        this.blocked_date = blocked_date;
        this.counter = counter;
        this.deleted = deleted;
        this.activated_account = activated_account;
        this.creation_time = creation_time;
        this.modification_time = modification_time;
        this.delete_time = delete_time;
        this.authorities = authorities;
    }


   /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authoriti = new ArrayList<GrantedAuthority>();

        *//*for (Status status : Status.values()) {
            authoriti.add(new SimpleGrantedAuthority(status.name()));
        }*//*
        authoriti.add(new SimpleGrantedAuthority(status.name()));
        return authoriti;
    }*/


    /*
     * GETTERS-SETTERS
     */

    public Long getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Long login_id) {
        this.login_id = login_id;
    }

    public Customers getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customers customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Employees getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Employees employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public Timestamp getBlocked_date() {
        return blocked_date;
    }

    public void setBlocked_date(Timestamp blocked_date) {
        this.blocked_date = blocked_date;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActivated_account() {
        return activated_account;
    }

    public void setActivated_account(boolean activated_account) {
        this.activated_account = activated_account;
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {

        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
