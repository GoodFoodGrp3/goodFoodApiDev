package com.goodfood.api.model;

public class Employees
{
    private int employee_id;
    private int co_employee_id;
    private int office_id;
    private int order_commodity;
    private int login_id;
    private String lastname;
    private String firstname;
    private String extension;
    private String email;
    private String role;
    private int reports_to;

    public Employees()
    {

    }

    public Employees(int employee_id, int co_employee_id, int office_id, int order_commodity, int login_id, String lastname, String firstname, String extension, String email, String role, int reports_to)
    {
        this.employee_id = employee_id;
        this.co_employee_id = co_employee_id;
        this.office_id = office_id;
        this.order_commodity = order_commodity;
        this.login_id = login_id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.extension = extension;
        this.email = email;
        this.role = role;
        this.reports_to = reports_to;
    }

    public int getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(int employee_id)
    {
        this.employee_id = employee_id;
    }

    public int getCo_employee_id()
    {
        return co_employee_id;
    }

    public void setCo_employee_id(int co_employee_id)
    {
        this.co_employee_id = co_employee_id;
    }

    public int getOffice_id()
    {
        return office_id;
    }

    public void setOffice_id(int office_id)
    {
        this.office_id = office_id;
    }

    public int getOrder_commodity()
    {
        return order_commodity;
    }

    public void setOrder_commodity(int order_commodity)
    {
        this.order_commodity = order_commodity;
    }

    public int getLogin_id()
    {
        return login_id;
    }

    public void setLogin_id(int login_id)
    {
        this.login_id = login_id;
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

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getReports_to()
    {
        return reports_to;
    }

    public void setReports_to(int reports_to)
    {
        this.reports_to = reports_to;
    }
}
