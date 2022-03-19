package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping(value = "")
    public List<Employees> getAll(){
        return this.employeesService.getAllEmployees();
    }

    @GetMapping( value = "/{id}" )
    public Employees getEmployeeById( @PathVariable int id ) {
        return this.employeesService.getEmployeeById( id );
    }
}
