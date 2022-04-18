package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Orders;
import com.goodfood.api.repositories.OrdersRepository;
import com.goodfood.api.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "OrdersService")
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public List<Orders> getAllOrders()
    {
        return (List<Orders>) this.ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(int id)
    {
        return this.ordersRepository.findById(id);
    }
}
