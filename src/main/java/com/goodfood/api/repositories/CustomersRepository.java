package com.goodfood.api.repositories;

import com.goodfood.api.entities.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {

    Customers findById (int id);

}
