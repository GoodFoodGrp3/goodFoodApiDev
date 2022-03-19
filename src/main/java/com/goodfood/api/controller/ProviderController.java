package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "")
    public List<Provider> getAllProviders() {
        return this.providerService.getAllProviders();
    }

    @GetMapping( value = "/{id}" )
    public Provider getProviderById(@PathVariable int id ) {
        return this.providerService.getProviderById( id );
    }


}
