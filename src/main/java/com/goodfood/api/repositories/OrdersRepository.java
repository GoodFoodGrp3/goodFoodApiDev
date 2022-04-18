package com.goodfood.api.repositories;

import com.goodfood.api.entities.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Integer>
{
    Orders findById(int id);
}
