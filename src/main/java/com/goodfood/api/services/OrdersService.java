package com.goodfood.api.services;

import com.goodfood.api.entities.Orders;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;

import java.util.List;

public interface OrdersService
{
    List<Orders> getAllOrders() throws OrderNotFoundException;
    Orders getOrderById(int id) throws OrderNotFoundException;
}
