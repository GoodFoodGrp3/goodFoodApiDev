package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;

import java.util.List;

public interface EmployeesService {

    List<Employees> getAllEmployees();
    Employees getEmployeeById( int id );
    Employees getEmployeesByFirstName( String username );
}
