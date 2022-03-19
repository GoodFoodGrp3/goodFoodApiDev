package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Products;
import com.goodfood.api.repositories.ProductsRepository;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "ProductService" )
public class ProductsServicesImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public List<Products> getAllProducts() {
        return (List<Products>) this.productsRepository.findAll();
    }

    @Override
    public Products getProductById(int id) {
        return this.productsRepository.findById(id);
    }
}
