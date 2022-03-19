package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Orders;
import com.goodfood.api.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping(value = "")
    public List<Orders> getAllOrders(){
        return this.ordersService.getAllOrders();
    }

    @GetMapping( value = "/{id}" )
    public Orders getOrderById(@PathVariable int id ) {
        return this.ordersService.getOrderById( id );
    }

}
