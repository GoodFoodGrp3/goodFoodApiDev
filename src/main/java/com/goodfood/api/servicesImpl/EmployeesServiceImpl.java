package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.repositories.EmployeesRepository;
import com.goodfood.api.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "EmployeesService" )
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    EmployeesRepository employeesRepository;

    @Override
    public List<Employees> getAllEmployees() {
        return (List<Employees>) this.employeesRepository.findAll();
    }

    @Override
    public Employees getEmployeeById(int id) {
        return this.employeesRepository.findById(id);
    }

    @Override
    public Employees getEmployeesByFirstName(String username) {
        return this.employeesRepository.findByFirstname(username);
    }
}
