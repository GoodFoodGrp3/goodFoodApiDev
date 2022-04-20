package com.goodfood.api.controller;


import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.entities.Status;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateProvidersForm;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private AuthenticationService authenticationService;


    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Provider> getAllProviders()
    {
        return this.providerService.getAllProviders();
    }

    @GetMapping(value = "/{id}")
    public Provider getProviderById(@PathVariable int id )
    {
        return this.providerService.getProviderById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    @PostMapping(value = "")
    public Provider createProviders(@RequestBody CreateProvidersForm createProvidersForm)
    {
        Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);

        return this.providerService.createProviders(createProvidersForm.getId(),
                createProvidersForm.getProvider_name(), createProvidersForm.getAddressline(),
                createProvidersForm.getEmail(), createProvidersForm.getPhone(),
                createProvidersForm.getCountry(), createProvidersForm.getPostal_code(),
                createProvidersForm.getState());
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") int id, String provider_name,
                                                   String addressline, String email, String phone, String country,
                                                   String postal_code, String state)
    {
        Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);

        return new ResponseEntity<>(this.providerService.updateProvider(id, provider_name, addressline, email, phone,country,postal_code, state), HttpStatus.OK);
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
