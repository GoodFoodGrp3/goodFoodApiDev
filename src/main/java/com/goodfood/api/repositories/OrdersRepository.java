package com.goodfood.api.repositories;

import com.goodfood.api.entities.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, String>
{
    List<Orders> findByCustomers(int id);
    Optional<Orders> findById(String id);
}
