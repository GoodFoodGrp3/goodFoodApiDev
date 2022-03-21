package com.goodfood.api.request.employee;

public class UpdateEmployeePasswordForm {

    private String password;

    private String cpassword;

    public UpdateEmployeePasswordForm() {

    }

    public UpdateEmployeePasswordForm(String password, String cpassword ) {
        super();
        this.password = password;
        this.cpassword = cpassword;
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
