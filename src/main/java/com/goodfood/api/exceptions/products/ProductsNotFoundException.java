package com.goodfood.api.exceptions.products;

import com.goodfood.api.exceptions.orders.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux produits.
 * </p>
 * @exception ProductsNotFoundException si le produit n'a pas été trouvé.
 * @author Gaëtan T.
 */
public class ProductsNotFoundException extends ResponseStatusException
{
    public ProductsNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
