package com.goodfood.api.controller;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Status;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateCategoriesForm;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    JwtUserDetailsService jwtUserDetailsService;

   // private AuthenticationService authenticationService;

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
    public Categories createCategories( @RequestBody CreateCategoriesForm createCategoriesForm )
    {
        /*Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);*/

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
            errorLogServices.recordLog( new Error_log( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
