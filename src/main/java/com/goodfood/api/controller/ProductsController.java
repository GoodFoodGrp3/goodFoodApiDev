package com.goodfood.api.controller;

import com.goodfood.api.entities.Products;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }
}