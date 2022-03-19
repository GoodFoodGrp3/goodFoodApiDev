package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Customers;
import com.goodfood.api.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping(value = "")
    public List<Customers> getAll(){
        return this.customersService.getAllCustomers();
    }

    @GetMapping( value = "/{id}" )
    public Customers getCustomerById( @PathVariable int id ) {
        return this.customersService.getCustomerById( id );
    }

}
