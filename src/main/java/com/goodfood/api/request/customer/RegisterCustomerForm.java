package com.goodfood.api.request.customer;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 *  Class qui permet de définir le formulaire de l'enregistrement des clients.
 * </p>
 * @author Gaëtan T.
 */
public class RegisterCustomerForm
{
    private int order;

    private int comment;

    //private int employee;
    /**
     * Champ mot de passe du formulaire.
     */
    @NotNull(message = "le mot de passe est requis")
    @Size(min = 7, message = "mot de passe trop court")
    private String password;

    /**
     * Champ confirmation du mot de passe du formulaire.
     */
    @NotNull(message = "le mot de passe est requis")
    private String cpassword;

    /**
     * Champ pseudo du formulaire.
     */
    @NotNull(message = "Un pseudo est requis")
    @NotBlank(message = "Un pseudo ne peut être vide")
    private String username;

    /**
     * Champ nom d'utilisateur du formulaire.
     */
    @NotNull(message = "Un nom est requis")
    @NotBlank(message = "Un nom ne peut être vide")
    private String lastname;

    /**
     * Champ prénom d'utilisateur du formulaire.
     */
    @NotNull(message = "Un prénom est requis")
    @NotBlank(message = "Un prénom ne peut être vide")
    private String firstname;

    /**
     * Champ téképhone de l'utilisateur du formulaire.
     */
    @NotNull(message = "Un numéro est requis")
    @Size(max = 10, message = "Numéro trop long")
    private String phone;

    /**
     * Champ addressline1 de l'utilisateur du formulaire.
     */
    @NotBlank(message = "l'adresse postal ne peut être vide")
    @NotNull(message = "l'adresse postal est requise")
    private String addressline1;

    /**
     * Champ addressline2 de l'utilisateur (non obligatoire) du formulaire.
     */
    private String addressline2;

    /**
     * Champ ville de l'utilisateur du formulaire.
     */
    @NotBlank(message = "la ville ne peut être vide")
    @NotNull(message = "la ville est requise")
    private String city;

    /**
     * Champ pays de l'utilisateur du formulaire.
     */
    private String state;

    /**
     * Champ code postal de l'utilisateur du formulaire.
     */
    @NotBlank(message = "le code postal ne peut être vide")
    @NotNull(message = "le code postal est requise")
    private String postalCode;

    /**
     * Champ région de l'utilisateur du formulaire.
     */
    @NotBlank(message = "le pays ne peut être vide")
    @NotNull(message = "le pays est requis")
    private String country;

    /**
     * Champ email de l'utilisateur du formulaire.
     */
    @NotBlank(message = "l'adresse mail ne peut être vide")
    @NotNull(message = "l'adresse mail est requise")
    @Email(message = "L'adresse n'est pas valide")
    private String email;


    public RegisterCustomerForm()
    {

    }


    // ***************
    // GETTER AND SETTER
    // ***************

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getComment()
    {
        return comment;
    }

    public void setComment(int comment)
    {
        this.comment = comment;
    }

    /*public int getEmployee()
    {
        return employee;
    }

    public void setEmployee(int employee)
    {
        this.employee = employee;
    }*/

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCpassword()
    {
        return cpassword;
    }

    public void setCpassword(String cpassword)
    {
        this.cpassword = cpassword;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
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
}
