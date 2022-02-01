package com.goodfood.api.dao;

import com.goodfood.api.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EmployeesDaoImpl implements EmployeesDAO
{
    private ArrayList<Employees> employeess = new ArrayList<>();

    @Override
    public void save(Employees employees)
    {
        employeess.add(employees);
    }

    @Override
    public void update(Employees employees)
    {

    }

    @Override
    public Employees findById(long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public ArrayList<Employees> findAll()
    {
        return employeess;
    }
}
