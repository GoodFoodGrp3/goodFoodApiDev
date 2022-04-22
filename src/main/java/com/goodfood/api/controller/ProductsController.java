package com.goodfood.api.controller;
import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Products;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateProductsForm;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private ProductService productService;

    @Autowired
    private ErrorLogServices errorLogServices;

    // ***************
    // GET
    // ***************

    @GetMapping("")
    public List<Products> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Products getCProductById(@PathVariable int id )
    {
        return this.productService.getProductById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    @PostMapping(value = "")
    public Products createProducts(@RequestBody CreateProductsForm createProductsForm)
    {
//        Status status = authenticationService.getCurrentEmployee().getStatus();
//        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);

        return this.productService.createProducts(createProductsForm.getId(), createProductsForm.getCategories(),
                createProductsForm.getProductName(), createProductsForm.getProductDescription(),
                createProductsForm.getQuantityInStock(), createProductsForm.getBuyPrice());
    }


    // ***************
    // DELETE
    // ***************

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void delete( @PathVariable(value = "id") int id )
    {
//        Status status = authenticationService.getCurrentEmployee().getStatus();
//        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);

        this.productService.deleteProductById(id);
    }

    // ***************
    // PUT/UPDATE
    // ***************

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Products> updateProduct(@PathVariable(value = "id") int id, int category_id,
                                                  String product_name, String product_description,
                                                  int quantity_in_stock, double buy_price)
    {
//        Status status = authenticationService.getCurrentEmployee().getStatus();
//        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);

        return new ResponseEntity<>(this.productService.updateProduct(id, category_id, product_name, product_description, quantity_in_stock,buy_price), HttpStatus.OK);
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