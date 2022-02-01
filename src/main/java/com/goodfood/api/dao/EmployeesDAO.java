package com.goodfood.api.dao;

import com.goodfood.api.model.Employees;

import java.util.ArrayList;

public interface EmployeesDAO
{
    void save(Employees employees);
    void update(Employees employees);
    Employees findById(long id);
    void delete(long id);

    ArrayList<Employees> findAll();
}
