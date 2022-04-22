package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.exceptions.employees.EmployeesNotFoundException;
import com.goodfood.api.request.employee.RegisterEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeePasswordForm;
import com.goodfood.api.request.employee.UpdateEmployeeStatusForm;

import java.util.List;

public interface EmployeesService
{
    Employees registerEmployee(RegisterEmployeeForm registerEmployeeForm);
    Employees getEmployeesByFirstName(String username);
    List<Employees> getAllEmployees() throws EmployeesNotFoundException;
    Employees getEmployeeById(int id) throws EmployeesNotFoundException;
    Employees getEmployeeByUserName(String username);
    LoginDao updatePassword(int id, UpdateEmployeePasswordForm updateEmployeePasswordForm);
    void deleteById(int id);
    Employees updateEmployeeProfile(int id, UpdateEmployeeForm updateEmployeeForm);
    LoginDao updateStatus(int id, UpdateEmployeeStatusForm updateEmployeeStatusForm);

    LoginDao getEmployeeByEmployeeNumber(int id);
}
