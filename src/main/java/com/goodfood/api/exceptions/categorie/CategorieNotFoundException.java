package com.goodfood.api.exceptions.categorie;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux catégories.
 * </p>
 * @exception CategorieNotFoundException si catégorie non trouvée.
 * @author Gaëtan T.
 */
public class CategorieNotFoundException extends ResponseStatusException
{
    public CategorieNotFoundException( String s ) {
        super( HttpStatus.NOT_FOUND, s );
    }
}
