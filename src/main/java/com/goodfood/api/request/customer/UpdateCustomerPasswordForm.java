package com.goodfood.api.request.customer;

public class UpdateCustomerPasswordForm {

    private String password;

    private String cpassword;

    public UpdateCustomerPasswordForm() {
    }

    public UpdateCustomerPasswordForm(String password, String cpassword) {
        this.password = password;
        this.cpassword = cpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}