package com.goodfood.api.services;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> getAllOrders();
}
