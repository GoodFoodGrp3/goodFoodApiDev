package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Products;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service ProductService.
 * </p>
 * @author Gaëtan T.
 */
public interface ProductService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de tous les produits.
     *
     * </p>
     * @exception ProductsNotFoundException si produit non trouvé.
     */
    List<Products> getAllProducts() throws ProductsNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de supprimer un produit par son id.
     *
     * </p>
     * @param id de la commande.
     */
    void deleteProductById(int id);

    /**
     * <p><b>Méthode</b> qui permet de get un produit par son id.
     *
     * </p>
     * @exception ProductsNotFoundException si produit non trouvé.
     * @param id de la commande.
     */
    Products getProductById (int id) throws ProductsNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de créer un produit par son id.
     *
     * </p>
     * @param id de la commande.
     * @param categories id de categorie.
     * @param productName de la commande.
     * @param productDescription de la commande.
     * @param quantityInStock de la commande.
     * @param buyPrice de la commande.
     */
    Products createProducts(int id, Categories categories, String productName, String productDescription ,
                            int quantityInStock, int buyPrice );

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un produit par son id.
     *
     * </p>
     * @param id de la commande.
     * @param category_id id de categorie.
     * @param product_name de la commande.
     * @param product_description de la commande.
     * @param quantity_in_stock de la commande.
     * @param buy_price de la commande.
     */
    Products updateProduct(int id, int category_id, String product_name, String product_description,
                           int quantity_in_stock, double buy_price);
}
