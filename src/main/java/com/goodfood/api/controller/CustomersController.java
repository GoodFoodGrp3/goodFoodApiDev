package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des clients.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe CustomersController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /customers </p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@RestController
@RequestMapping("/customers")
public class CustomersController
{

    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet CustomersService qui représente la class CustomersService.
     */
    @Autowired
    private CustomersService customersService;

    /**
     * Déclaration de l'objet CustomersRepository qui représente la class CustomersRepository.
     */
    @Autowired
    private CustomersRepository customersRepository;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous un client par son nom d'utilisateur.
     *
     * </p>
     * <p>La value = "/profile/search/{username}" spécifie que pour y accéder la route est : /customers/profile/search/{username}.</p>
     * @apiNote méthode GET.
     * @param username du client.
     * @return un client par son nom d'utilisateur.
     */
    @GetMapping( value = "/profile/search/{username}" )
    public Customers getCustomerByUsername(@PathVariable String username )
    {
        return customersService.getCustomerByUserName(username);
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les clients.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /customers.</p>
     * @apiNote méthode GET.
     * @return tous les clients.
     */
    @GetMapping(value = "")
    public List<Customers> getAllCustomers()
    {
        return this.customersService.getAllCustomers();
    }


    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le status du client.
     *
     * </p>
     * <p>La value = "/status/{username}" spécifie que pour y accéder la route est : /customers/status/{username}.</p>
     * @apiNote méthode GET.
     * @param username du client.
     * @return le status du client.
     */
    @GetMapping(value = "/status/{username}")
    public String getStatus(@PathVariable String username)
    {
        return this.customersService.getStatus( username );
    }


    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le client par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /customers/{id}.</p>
     * @apiNote méthode GET.
     * @param id du client.
     * @return le client par son id.
     */
    @GetMapping( value = "/{id}" )
    public Customers getCustomerById( @PathVariable int id )
    {
        return this.customersService.getCustomerById(id);
    }


    /**
     * <p><b>Méthode/Route</b> qui permet de récupérer le client actuellement connecté.
     *
     * </p>
     * <p>La value = "/current" spécifie que pour y accéder la route est : /customers/current.</p>
     * @apiNote méthode GET.
     * @return le client actuellemnt connecté.
     * @return le status http.
     */
    @GetMapping( "/current" )
    public ResponseEntity<Customers> getCurrentCustomer() {
        return new ResponseEntity<>( this.customersService.getCurrentCustomer(), HttpStatus.OK );
    }

    // ***************
    // POST/REGISTER/LOGIN
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le client venant de s'enregistrer.
     *
     * </p>
     * <p>La value = "/register" spécifie que pour y accéder la route est : /customers/register.</p>
     * @apiNote méthode POST.
     * @param registerCustomerForm formulaire de création.
     * @return le client venant de s'enregistrer.
     * @return le status http.
     */
    @PostMapping( value = "/register" )
    public ResponseEntity<Customers> registerCustomer(@Valid @RequestBody RegisterCustomerForm registerCustomerForm)
    {
        //constraintViolationCheck( errors, request );

        return new ResponseEntity<Customers>(customersService.registerCustomer(registerCustomerForm), HttpStatus.OK);
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de supprimer le client par son id.
     *
     * </p>
     * <p>La value = "/profile/{id}" spécifie que pour y accéder la route est : /customers/profile/{id}.</p>
     * @apiNote méthode DELETE.
     * @param id du client à supprimer.
     */
    @DeleteMapping(value = "/profile/{id}")
    public void deleteCustomerById(@PathVariable int id)
    {
        this.customersService.deleteById(id);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le client ayant été mise à jour.
     *
     * </p>
     * <p>La value = "/profile/{id}" spécifie que pour y accéder la route est : /customers/profile/{id}.</p>
     * @apiNote méthode PUT.
     * @param id du client à mettre à jour.
     * @param updateCustomerForm formulaire de mise à jour client.
     * @return l'id du client et les informations du formulaire du client,mise à jour.
     */
    @PutMapping(value = "/profile/{id}")
    public Customers updateCustomerById(@PathVariable int id, @Valid @RequestBody UpdateCustomerForm updateCustomerForm)
    {
        return customersService.updateCustomerProfile(id, updateCustomerForm);
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour le mot de passe du client.
     *
     * </p>
     * <p>La value = "/profile/{id}/password" spécifie que pour y accéder la route est : /profile/{id}/password.</p>
     * @apiNote méthode PUT.
     * @param id du client dont le mot de passe doit se mettre à jour.
     * @param updateCustomerPassword formulaire de mise à jour du mot de passe client.
     * @return l'id du client et le mot de passe mise à jour du client.
     */
    @PutMapping(value = "/profile/{id}/password")
    public LoginDao updateCustomerPassword(@PathVariable int id,
                                           @RequestBody UpdateUserPasswordForm updateCustomerPassword)
    {

        return customersService.updatePassword(id, updateCustomerPassword);
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck( Errors errors, HttpServletRequest request )
    {
        if (errors.hasErrors())
        {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();

            for (ObjectError e : errors.getAllErrors())
            {
                violationsList.add( e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";

            for (ConstraintViolation<?> violation : violationsList)
            {
                if (violationsList.indexOf( violation) > 0 )
                {
                    exceptionMessage += " | ";
                }

                exceptionMessage += violation.getMessage();
            }

            errorLogServices.recordLog(new ErrorLog(request.getHeader("Host"), HttpStatus.BAD_REQUEST, exceptionMessage));
            throw new ConstraintViolationException(exceptionMessage);
        }
    }
}
