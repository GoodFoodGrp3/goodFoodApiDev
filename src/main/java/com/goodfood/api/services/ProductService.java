package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Products;

import java.util.List;


public interface ProductService {

    List<Products> getAllProducts();
    void deleteProductById( int id );
    Products getProductById (int id);
    Products createProducts(int id, Categories categories, String productName, String productDescription , int quantityInStock, int buyPrice );
}
