package com.goodfood.api.repositories;

import com.goodfood.api.entities.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Integer> {

    Employees findById (int id);
}
