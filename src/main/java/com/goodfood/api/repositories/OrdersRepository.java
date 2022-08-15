package com.goodfood.api.repositories;

import com.goodfood.api.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String>
{
    Orders findById(String id);
    List<Orders> findByCustomerId(int id);
}
