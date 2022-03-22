package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.request.employee.RegisterEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeePasswordForm;

import java.util.List;

public interface EmployeesService {

    //Register
    Employees registerEmployee(RegisterEmployeeForm registerEmployeeForm);

    //Login
    Employees getEmployeesByFirstName( String username );

    List<Employees> getAllEmployees();
    Employees getEmployeeById( int id );

    Employees getEmployeeByUserName(String username);

    Employees updatePassword(int id, UpdateEmployeePasswordForm updateEmployeePasswordForm);
}
