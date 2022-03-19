package com.goodfood.api.services;

import com.goodfood.api.entities.Products;

import java.util.List;


public interface ProductService {

    List<Products> getAllProducts();
    Products getProductById (int id);

}
