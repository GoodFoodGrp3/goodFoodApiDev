package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.employee.RegisterEmployeeForm;

import java.util.List;

public interface CustomersService {

    //Register
    Customers registerCustomer(RegisterCustomerForm registerCustomerForm);

    List<Customers> getAllCustomers();
    Customers getCustomerById( int id );

}
