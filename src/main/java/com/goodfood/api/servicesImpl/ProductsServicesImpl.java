package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Products;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.ProductsRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service(value = "ProductService")
public class ProductsServicesImpl implements ProductService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Products> getAllProducts() throws ProductsNotFoundException
    {
        List<Products> getAllProducts = (List<Products>) productsRepository.findAll();

        if (getAllProducts == null || getAllProducts.isEmpty())
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND, "Aucun produits trouvé"));
            throw new ProductsNotFoundException( "Aucun produits trouvé" );
        }

        else
        {
            return getAllProducts;
        }
    }

    @Override
    public Products getProductById(int id) throws ProductsNotFoundException
    {
        Products products = productsRepository.findById(id);

        if (products == null)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND, "Le produit n° " + id
                    + " est introuvable" ) );
            throw new ProductsNotFoundException( "Le produit n° " + id + " est introuvable" );
        }

        else
        {
            return products;
        }
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteProductById(int id)
    {
        Products products = this.productsRepository.findById(id);

        if (products == null)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None product could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None product could be found with the id %d", id));
        }

        this.productsRepository.deleteById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Products createProducts(int id, Categories categories, String productName, String productDescription,
                                   int quantityInStock, int buyPrice)
    {
        final Products products = new Products(categories,productName,productDescription,quantityInStock,buyPrice);
        return  this.productsRepository.save(products);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Products updateProduct(int id, int category_id, String product_name, String product_description,
                                  int quantity_in_stock, double buy_price)
    {
        Products products = this.productsRepository.findById(id);

        if (products == null)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None product could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None product could be found with the id %d", id));
        }

        products.setProduct_name(product_name);
        products.setProduct_description(product_description);
        products.setQuantity_in_stock(quantity_in_stock);
        products.setBuy_price(buy_price);
        productsRepository.save(products);
        return products;
    }
}
