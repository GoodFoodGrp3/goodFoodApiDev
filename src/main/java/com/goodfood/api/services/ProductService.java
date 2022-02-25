package com.goodfood.api.services;

import com.goodfood.api.entities.Products;
import com.goodfood.api.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        return (List<Products>) productsRepository.findAll();
    }
}
