package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "CustomersService" )
public class CustomersServicesImpl implements CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    @Override
    public List<Customers> getAllCustomers() {
        return (List<Customers>) this.customersRepository.findAll();
    }

    @Override
    public Customers getCustomerById(int id) {
        return this.customersRepository.findById(id);
    }
}
