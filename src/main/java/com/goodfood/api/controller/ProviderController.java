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


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des fournisseurs.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe ProviderController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /providers </p>
 * @author Gaëtan T.
 */
@RestController
@CrossOrigin( "*" )
@RequestMapping("/providers")
public class ProviderController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet ProviderService qui représente la class ProviderService.
     */
    @Autowired
    private ProviderService providerService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les fournisseurs.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /providers.</p>
     * @apiNote méthode GET.
     * @return tous les fournisseurs.
     */
    @GetMapping(value = "")
    public List<Provider> getAllProviders()
    {
        return this.providerService.getAllProviders();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner un fournisseur par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /providers/{id}.</p>
     * @apiNote méthode GET.
     * @param id du fournisseur.
     * @return un fournisseur selon l'id.
     */
    @GetMapping(value = "/{id}")
    public Provider getProviderById(@PathVariable int id )
    {
        return this.providerService.getProviderById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de créer un fournisseur.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /providers.</p>
     * @apiNote méthode POST.
     * @param createProvidersForm formulaire de création d'un fournisseur.
     * @return un fournisseur créer.
     */
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

    /**
     * <p><b>Méthode/Route</b> qui permet de créer un fournisseur.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /providers/{id}.</p>
     * @apiNote méthode PUT.
     * @param id du fournisseur.
     * @param provider_name du fournisseur.
     * @param addressline du fournisseur.
     * @param email du fournisseur.
     * @param phone du fournisseur.
     * @param country du fournisseur.
     * @param postal_code du fournisseur.
     * @param state du fournisseur.
     * @return un fournisseur mise à jour.
     */
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
