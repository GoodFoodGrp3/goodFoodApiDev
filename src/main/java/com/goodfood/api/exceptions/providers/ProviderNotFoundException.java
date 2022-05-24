package com.goodfood.api.exceptions.providers;

import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux fournisseurs.
 * </p>
 * @exception ProviderNotFoundException si le fournisseur n'a pas été trouvé.
 * @author Gaëtan T.
 */
public class ProviderNotFoundException extends ResponseStatusException
{
    public ProviderNotFoundException( String s )
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
