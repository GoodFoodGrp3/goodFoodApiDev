package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Products;

import java.util.List;


public interface ProductService {

    List<Products> getAllProducts();
    void deleteProductById( int id );
    Products getProductById (int id);
    Products createProducts(int id, Categories categories, String productName, String productDescription , int quantityInStock, int buyPrice );

    Products updateProvider(int id, int category_id, String product_name, String product_description, int quantity_in_stock, double buy_price);
}
