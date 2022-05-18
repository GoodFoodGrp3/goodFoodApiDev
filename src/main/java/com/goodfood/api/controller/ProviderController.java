package com.goodfood.api.controller;


import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.entities.Status;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.employee.CreateProvidersForm;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProviderService;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin( "http://localhost:4200" )
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
    private LoginRepository loginRepository;

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
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String login = authentication.getName();

        LoginDao user = loginRepository.findByLogin(login);

        generatePrivilegeErrorIf(user.getStatus() != Status.RESTAURATEUR && user.getStatus() != Status.EMPLOYEE && user.getStatus() != Status.ADMINISTRATEUR);*/

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

        return new ResponseEntity<>(this.providerService.updateProvider(id, provider_name, addressline, email, phone,country,postal_code, state), HttpStatus.OK);
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
