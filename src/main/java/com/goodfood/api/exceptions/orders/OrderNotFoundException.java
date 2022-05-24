package com.goodfood.api.exceptions.orders;

import com.goodfood.api.exceptions.offices.OfficesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux commandes.
 * </p>
 * @exception OrderNotFoundException si la commande n'a pas été trouvé.
 * @author Gaëtan T.
 */
public class OrderNotFoundException extends ResponseStatusException
{
    public OrderNotFoundException( String s )
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
