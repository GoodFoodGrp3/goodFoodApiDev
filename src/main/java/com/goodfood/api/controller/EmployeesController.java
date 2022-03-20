package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.request.JwtResponse;
import com.goodfood.api.request.member.LoginForm;
import com.goodfood.api.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    /*@PostMapping( value = "/login" )
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm credentials, HttpServletRequest request ) {

    }*/
}
