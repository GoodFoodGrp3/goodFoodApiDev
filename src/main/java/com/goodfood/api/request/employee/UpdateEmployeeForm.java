package com.goodfood.api.request.employee;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateEmployeeForm {

    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String username;

    @NotBlank( message = "Un numéro ne peut être vide" )
    @Size( min = 4, max=4)
    private String private_number;

    @NotBlank( message = "Un email ne peut être vide" )
    private String email;

    public UpdateEmployeeForm() {

    }

    public UpdateEmployeeForm(String username, String private_number, String email) {
        this.username = username;
        this.private_number = private_number;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
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
}
