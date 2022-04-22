package com.goodfood.api.request;

public class UpdateUserPasswordForm
{
    private String password;

    private String cpassword;


    // ***************
    // CONSTRUCTOR
    // ***************

    public UpdateUserPasswordForm()
    {

    }

    public UpdateUserPasswordForm(String password, String cpassword )
    {
        super();
        this.password = password;
        this.cpassword = cpassword;
    }


    // ***************
    // GETTER AND SETTER
    // ***************

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
}
