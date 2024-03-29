package com.goodfood.api.repositories;

import com.goodfood.api.entities.Employees;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeesRepository extends CrudRepository<Employees, Integer>
{
    Employees findById(int id);
    Employees findByFirstname(String username);
    Employees findByEmail(String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE employees SET password = :password WHERE employee_id = :id")
    void updatePassword(@Param(value = "id") int id, @Param(value = "password") String password);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE employees SET firstname = firstname, lastname = lastname, private_number = private_number, email = email WHERE employee_id = :id")
    void updateProfile(@Param(value = "id") int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE employees SET status = :status WHERE id = :id")
    void updateStatus(@Param(value = "id") int id, @Param(value = "status") String status);
}
