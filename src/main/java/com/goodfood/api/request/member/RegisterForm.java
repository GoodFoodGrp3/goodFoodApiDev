package com.goodfood.api.request.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {

    @NotNull( message = "Un pseudo est requis" )
    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String username;

    @NotBlank( message = "l'adresse mail ne peut être vide" )
    @NotNull( message = "l'adresse mail est requise" )
    @Email( message = "L'adresse n'est pas valide" )
    private String email;

    @NotBlank( message = "le champ ne peut être vide" )
    @NotNull( message = "Une succursale est requise" )
    private String succursale;

    @NotNull( message = "le mot de passe est requis" )
    @Size( min = 7, message = "mot de passe trop court" )
    private String password;

    @NotNull( message = "le mot de passe est requis" )
    private String cpassword;

    public RegisterForm() {

    }

    public RegisterForm(String username, String email, String succursale,String password, String cpassword ) {
        super();
        this.username = username;
        this.succursale = succursale;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
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

    public String getSuccursale() {
        return succursale;
    }

    public void setSuccursale(String succursale) {
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
