package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.exceptions.customers.CustomersNotFoundException;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerPasswordForm;


import java.util.List;

public interface CustomersService
{
    Customers registerCustomer(RegisterCustomerForm registerCustomerForm);
    List<Customers> getAllCustomers() throws CustomersNotFoundException;
    Customers getCustomerById(int id) throws CustomersNotFoundException;
    Customers getCustomerByUserName(String username);
    void deleteById(int id);
    Customers updateCustomerProfile(int id, UpdateCustomerForm updateCustomerForm);
    Customers updatePassword(int id, UpdateCustomerPasswordForm updateCustomerPassword);
}
