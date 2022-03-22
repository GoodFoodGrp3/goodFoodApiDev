package com.goodfood.api.request.employee;

import com.goodfood.api.entities.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterEmployeeForm {

    @NotNull( message = "Un statut est requis" )
    @Enumerated( EnumType.STRING )
    private Status status;

    @NotNull( message = "Un pseudo est requis" )
    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String username;

    @NotBlank( message = "l'adresse mail ne peut être vide" )
    @NotNull( message = "l'adresse mail est requise" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    private int succursale;

    @NotNull( message = "le mot de passe est requis" )
    @Size( min = 7, message = "mot de passe trop court" )
    private String password;

    @NotNull( message = "le mot de passe est requis" )
    private String cpassword;

    public RegisterEmployeeForm() {

    }

    public RegisterEmployeeForm(Status status, String username, String email, int succursale, String password, String cpassword ) {
        super();
        this.status = status;
        this.username = username;
        this.succursale = succursale;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public int getSuccursale() {
        return succursale;
    }

    public void setSuccursale(int succursale) {
        this.succursale = succursale;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword( String cpassword ) {
        this.cpassword = cpassword;
    }

}
