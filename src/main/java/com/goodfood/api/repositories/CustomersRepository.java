package com.goodfood.api.repositories;

import com.goodfood.api.entities.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {

    //Customers findByFirstname(String username);
    Customers findById (int id);
    Customers findByEmail(String email);
}
