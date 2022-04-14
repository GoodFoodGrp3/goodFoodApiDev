package com.goodfood.api.controller;
import com.goodfood.api.entities.Products;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.request.employee.CreateProductsForm;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Products> updateProduct(@PathVariable( value = "id" ) int id, int category_id, String product_name, String product_description, int quantity_in_stock, double buy_price ) {
        return new ResponseEntity<>( this.productService.updateProvider( id, category_id, product_name, product_description, quantity_in_stock,buy_price), HttpStatus.OK );
    }

}