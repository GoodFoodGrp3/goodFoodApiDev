package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.request.member.RegisterForm;

import java.util.List;

public interface EmployeesService {

    //Register
    Employees registerMember(RegisterForm registerForm);

    //Login
    Employees getEmployeesByFirstName( String username );

    List<Employees> getAllEmployees();
    Employees getEmployeeById( int id );

    Employees getMemberByUserName(String username);
}
