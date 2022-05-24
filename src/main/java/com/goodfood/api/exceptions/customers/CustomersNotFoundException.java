package com.goodfood.api.exceptions.customers;

import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux clients.
 * </p>
 * @exception CustomersNotFoundException si client non trouvé.
 * @author Gaëtan T.
 */
public class CustomersNotFoundException extends ResponseStatusException
{
    public CustomersNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
