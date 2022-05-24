package com.goodfood.api.exceptions.commodity;

import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>
 *  Class qui permet de définir des exceptions lié aux matières premières.
 * </p>
 * @exception CommodityNotFoundException si matière première non trouvée.
 * @author Gaëtan T.
 */
public class CommodityNotFoundException extends ResponseStatusException
{
    public CommodityNotFoundException(String s)
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
