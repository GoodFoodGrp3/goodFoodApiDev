package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;


import java.util.List;

public interface CustomersService {

    //Register
    Customers registerCustomer(RegisterCustomerForm registerCustomerForm);

    List<Customers> getAllCustomers();
    Customers getCustomerById( int id );

    Customers getCustomerByUserName(String username);

    void deleteById(int id);

    Customers updateCustomerProfile(int id, UpdateCustomerForm updateCustomerForm);
}
