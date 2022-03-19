package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;

import java.util.List;

public interface CustomersService {

    List<Customers> getAllCustomers();
    Customers getCustomerById( int id );
}
