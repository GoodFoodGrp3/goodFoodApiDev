package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Products;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

   /* @PostMapping( value = "" )
    public Products createProducts(@RequestBody CreateProductsForm createProductsForm ) {
        return this.productService.createCategories( createCategoriesForm.getId(), createCategoriesForm.getCategoryName(), createCategoriesForm.getTextDescription(), createCategoriesForm.getHtmlDescription(), createCategoriesForm.getImage());
    }*/
}