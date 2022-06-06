package com.goodfood.api.request.employee;

import com.goodfood.api.entities.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 *  Class qui permet de définir le formulaire d'enregistrement des employers.
 * </p>
 * @author Gaëtan T.
 */
public class RegisterEmployeeForm
{
    /**
     * Champ status de l'employer du formulaire.
     */
    @NotNull(message = "Un statut est requis")
//    @NotBlank( message = "Un statut ne peut être vide" )
    @Enumerated( EnumType.STRING )
    private Status status;

    /**
     * Champ pseudo de l'employer du formulaire.
     */
    @NotNull(message = "Un email est requis")
    @NotBlank(message = "Un email ne peut être vide")
    private String username;

    /**
     * Champ nom de l'employer du formulaire.
     */
    @NotNull(message = "Un nom d'utilisateur est requis")
    @NotBlank(message = "Un nom ne peut être vide")
    private String lastname;

    /**
     * Champ email de l'employer du formulaire.
     */
    @NotBlank(message = "l'adresse mail ne peut être vide")
    @NotNull(message = "l'adresse mail est requise")
    @Email(message = "L'adresse n'est pas valide")
    private String email;

    /**
     * Champ succursale de l'employer du formulaire.
     */
    private int succursale;

    /**
     * Champ mot de passe de l'employer du formulaire.
     */
    @NotNull(message = "le mot de passe est requis")
    @Size(min = 7, message = "mot de passe trop court")
    private String password;

    /**
     * Champ de confirmation du mot de passe de l'employer du formulaire.
     */
    @NotNull(message = "le mot de passe est requis")
    @NotBlank(message = "le mot de passe ne peut être vide")
    private String cpassword;


    // ***************
    // CONSTRUCTOR
    // ***************

    public RegisterEmployeeForm()
    {

    }

    public RegisterEmployeeForm(Status status, String username, String lastname, String email, int succursale, String password, String cpassword) {
        this.status = status;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.succursale = succursale;
        this.password = password;
        this.cpassword = cpassword;
    }


// ***************
    // GETTER AND SETTER
    // ***************

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public int getSuccursale()
    {
        return succursale;
    }

    public void setSuccursale(int succursale)
    {
        this.succursale = succursale;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getCpassword()
    {
        return cpassword;
    }

    public void setCpassword( String cpassword )
    {
        this.cpassword = cpassword;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
