package com.goodfood.api.controller;
import com.goodfood.api.entities.Products;
import com.goodfood.api.request.employee.CreateProductsForm;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;


    @GetMapping("")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping( value = "/{id}" )
    public Products getCProductById(@PathVariable int id ) {
        return this.productService.getProductById( id );
    }

    @PostMapping( value = "" )
    public Products createProducts(@RequestBody CreateProductsForm createProductsForm ) {
        return this.productService.createProducts( createProductsForm.getId(), createProductsForm.getCategories(), createProductsForm.getProductName(), createProductsForm.getProductDescription(), createProductsForm.getQuantityInStock(), createProductsForm.getBuyPrice());
    }

    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete( @PathVariable( value = "id" ) int id ) {

        /*Status status = authentificationService.getCurrentUser().getStatus();
        generatePrivilegeErrorIf( status == Status.MEMBER );*/

        this.productService.deleteProductById( id );
    }

}