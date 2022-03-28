package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.request.customer.RegisterCustomerForm;


import java.util.List;

public interface CustomersService {

    //Register
    Customers registerCustomer(RegisterCustomerForm registerCustomerForm);

    List<Customers> getAllCustomers();
    Customers getCustomerById( int id );

    Customers getCustomerByUserName(String username);
}
