package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.employee.CreateCategoriesForm;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    LoginRepository loginRepository;


    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Categories> getAllCategories()
    {
        return this.categoriesService.getAllCategories();
    }

    @GetMapping( value = "/{id}" )
    public Categories getCategorieById(@PathVariable int id )
    {
        return this.categoriesService.getCategorieById( id );
    }

    // ***************
    // POST/CREATE
    // ***************

    @PostMapping( value = "" )
    public Categories createCategories(@RequestBody JwtRequest authenticationRequest, CreateCategoriesForm createCategoriesForm )
    {
       /* LoginDao user = loginRepository.findByLogin(authenticationRequest.getUsername());

        if(user.getStatus() != Status.ADMINISTRATEUR || user.getStatus() != Status.RESTAURATEUR || user.getStatus() != Status.EMPLOYEE )
        {
            return this.categoriesService.createCategories( createCategoriesForm.getId(),
                    createCategoriesForm.getCategoryName(), createCategoriesForm.getTextDescription(),
                    createCategoriesForm.getHtmlDescription(), createCategoriesForm.getImage());
        }*/

        return this.categoriesService.createCategories( createCategoriesForm.getId(),
                createCategoriesForm.getCategoryName(), createCategoriesForm.getTextDescription(),
                createCategoriesForm.getHtmlDescription(), createCategoriesForm.getImage());


    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
