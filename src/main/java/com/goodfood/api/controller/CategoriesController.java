package com.goodfood.api.controller;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateCategoriesForm;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(value = "")
    public List<Categories> getAllCategories() {
        return this.categoriesService.getAllCategories();
    }

    @GetMapping( value = "/{id}" )
    public Categories getCategorieById(@PathVariable int id ) {
        return this.categoriesService.getCategorieById( id );
    }

    @PostMapping( value = "" )
    public Categories createCategories( @RequestBody CreateCategoriesForm createCategoriesForm ) {
        return this.categoriesService.createCategories( createCategoriesForm.getId(), createCategoriesForm.getCategoryName(), createCategoriesForm.getTextDescription(), createCategoriesForm.getHtmlDescription(), createCategoriesForm.getImage());
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test ) {
        if ( test ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
