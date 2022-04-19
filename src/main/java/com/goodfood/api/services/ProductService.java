package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Products;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;

import java.util.List;


public interface ProductService
{
    List<Products> getAllProducts() throws ProductsNotFoundException;
    void deleteProductById(int id);
    Products getProductById (int id) throws ProductsNotFoundException;

    Products createProducts(int id, Categories categories, String productName, String productDescription ,
                            int quantityInStock, int buyPrice );

    Products updateProduct(int id, int category_id, String product_name, String product_description,
                           int quantity_in_stock, double buy_price);
}
