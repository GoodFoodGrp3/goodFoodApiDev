package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.request.employee.RegisterEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeePasswordForm;

import java.util.List;

public interface EmployeesService
{
    Employees registerEmployee(RegisterEmployeeForm registerEmployeeForm);
    Employees getEmployeesByFirstName(String username);
    List<Employees> getAllEmployees();
    Employees getEmployeeById(int id);
    Employees getEmployeeByUserName(String username);
    Employees updatePassword(int id, UpdateEmployeePasswordForm updateEmployeePasswordForm);
    void deleteById(int id);
    Employees updateEmployeeProfile(int id, UpdateEmployeeForm updateEmployeeForm);
}
