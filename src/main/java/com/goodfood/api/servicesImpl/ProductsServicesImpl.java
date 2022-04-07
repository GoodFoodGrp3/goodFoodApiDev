package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Products;
import com.goodfood.api.repositories.ProductsRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service( value = "ProductService" )
public class ProductsServicesImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;


    @Autowired
    private ErrorLogServices errorLogServices;

    @Override
    public List<Products> getAllProducts() {
        return (List<Products>) this.productsRepository.findAll();
    }

    @Override
    public void deleteProductById(int id) {
        Products products = this.productsRepository.findById( id );
        if ( products == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) );
        }

        this.productsRepository.deleteById( id );
    }

    @Override
    public Products getProductById(int id) {
        return this.productsRepository.findById(id);
    }

    @Override
    public Products createProducts(int id, Categories categories, String productName, String productDescription, int quantityInStock, int buyPrice) {
        final Products products = new Products(categories,productName,productDescription,quantityInStock,buyPrice);
        return  this.productsRepository.save(products);
    }


}
