package com.goodfood.api.controller;


import com.goodfood.api.entities.Provider;
import com.goodfood.api.request.employee.CreateProvidersForm;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping( value = "" )
    public Provider createProviders(@RequestBody CreateProvidersForm createProvidersForm ) {
        return this.providerService.createProviders( createProvidersForm.getId(),
                createProvidersForm.getProvider_name(), createProvidersForm.getAddressline(),
                createProvidersForm.getEmail(), createProvidersForm.getPhone(),
                createProvidersForm.getCountry(), createProvidersForm.getPostal_code(),
                createProvidersForm.getState());
    }

}
