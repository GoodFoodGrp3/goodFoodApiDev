package com.goodfood.api.controller;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Products;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateProductsForm;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des produits.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe ProductsController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /products </p>
 * @author Gaëtan T.
 */
@RestController
@CrossOrigin( "http://localhost:4200" )
@RequestMapping("/products")
public class ProductsController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet ProductService qui représente la class ProductService.
     */
    @Autowired
    private ProductService productService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;

    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les produits.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /products.</p>
     * @apiNote méthode GET.
     * @return tous les produits.
     */
    @GetMapping("")
    public List<Products> getAllProducts()
    {
        return productService.getAllProducts();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner un produit par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /products/{id}.</p>
     * @apiNote méthode GET.
     * @param id du produit.
     * @return un produit par son id.
     */
    @GetMapping(value = "/{id}")
    public Products getCProductById(@PathVariable int id )
    {
        return this.productService.getProductById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de créer un produit.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /products.</p>
     * @apiNote méthode POST.
     * @param createProductsForm formulaire pour créer un produit.
     * @return un produit créer.
     */
    @PostMapping(value = "")
    public Products createProducts(@RequestBody CreateProductsForm createProductsForm)
    {
        return this.productService.createProducts(createProductsForm.getId(), createProductsForm.getCategories(),
                createProductsForm.getProductName(), createProductsForm.getProductDescription(),
                createProductsForm.getQuantityInStock(), createProductsForm.getBuyPrice());
    }


    // ***************
    // DELETE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de supprimer un produit.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /products/{id}.</p>
     * @apiNote méthode DELETE.
     * @param id du produit.
     * @return un produit supprimer.
     */
    @DeleteMapping(value = "/{id}")
    @Transactional
    public void delete( @PathVariable(value = "id") int id )
    {
        this.productService.deleteProductById(id);
    }

    // ***************
    // PUT/UPDATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour un produit.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /products/{id}.</p>
     * @apiNote méthode PUT.
     * @param id du produit.
     * @param category_id du produit.
     * @param product_name du produit.
     * @param product_description du produit.
     * @param quantity_in_stock du produit.
     * @param buy_price du produit.
     * @return un produit mise à jour.
     */
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Products> updateProduct(@PathVariable(value = "id") int id, int category_id,
                                                  String product_name, String product_description,
                                                  int quantity_in_stock, double buy_price)
    {
        return new ResponseEntity<>(this.productService.updateProduct(id, category_id, product_name, product_description, quantity_in_stock,buy_price), HttpStatus.OK);
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}