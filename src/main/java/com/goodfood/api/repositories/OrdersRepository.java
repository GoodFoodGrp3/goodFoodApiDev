package com.goodfood.api.repositories;

import com.goodfood.api.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, String>
{
    Orders findById(int id);
}
