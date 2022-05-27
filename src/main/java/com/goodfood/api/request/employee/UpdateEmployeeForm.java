package com.goodfood.api.request.employee;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 *  Class qui permet de définir le formulaire de la mise à jour d'un employers.
 * </p>
 * @author Gaëtan T.
 */
public class UpdateEmployeeForm
{
    /**
     * Champ prénom de l'employer du formulaire.
     */
    @NotBlank(message = "Un prénom/pseudo ne peut être vide")
    private String firstname;

    /**
     * Champ numéro interne de l'employer du formulaire.
     */
    @NotBlank(message = "Un numéro ne peut être vide")
    @Size( min = 4, max=4)
    private String private_number;

    /**
     * Champ nom de l'employer du formulaire.
     */
    @NotBlank(message = "Un nom ne peut être vide")
    private String lastname;

    /**
     * Champ email de l'employer du formulaire.
     */
    @NotBlank(message = "Un email ne peut être vide")
    @Email(message = "L'adresse n'est pas valide")
    private String email;


    // ***************
    // CONSTRUCTOR
    // ***************

    public UpdateEmployeeForm()
    {

    }

    public UpdateEmployeeForm(String firstname, String private_number, String lastname, String email)
    {
        this.firstname = firstname;
        this.private_number = private_number;
        this.lastname = lastname;
        this.email = email;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public String getfirstname()
    {
        return firstname;
    }

    public void setfirstname( String firstname )
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
