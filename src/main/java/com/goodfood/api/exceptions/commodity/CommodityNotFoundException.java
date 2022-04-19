package com.goodfood.api.exceptions.commodity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommodityNotFoundException extends ResponseStatusException
{
    public CommodityNotFoundException(String s)
    {
        super(HttpStatus.NOT_FOUND, s);
    }
}
