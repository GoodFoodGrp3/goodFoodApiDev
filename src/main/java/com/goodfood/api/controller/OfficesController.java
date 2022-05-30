package com.goodfood.api.controller;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateOfficesForm;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des offices.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe OfficesController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /offices </p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@RestController
@RequestMapping("/offices")
public class OfficesController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet OfficesService qui représente la class OfficesService.
     */
    @Autowired
    private OfficesService officesService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;

    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les offices.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /offices.</p>
     * @return tous les offices.
     */
    @GetMapping(value = "")
    public List<Offices> getAll()
    {
        return this.officesService.getAllOffices();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner un office par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /offices/{id}.</p>
     * @param id de l'office.
     * @return un office par son id.
     */
    @GetMapping(value = "/{id}")
    public Offices getOfficeById(@PathVariable int id)
    {
        return this.officesService.getOfficeById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de créer un office.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /offices.</p>
     * @param createOfficesForm formulaire de création d'un office.
     * @return l'office créer.
     */
    @PostMapping(value = "")
    public Offices createOffices(@RequestBody CreateOfficesForm createOfficesForm)
    {
        return this.officesService.createOffices(createOfficesForm.getId(), createOfficesForm.getCity(),
                createOfficesForm.getPhone(), createOfficesForm.getAddressline1(),
                createOfficesForm.getAddressline2(), createOfficesForm.getState(),
                createOfficesForm.getCountry(),createOfficesForm.getPostal_code());
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour un office.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /offices/{id}.</p>
     * @param id de l'office à mettre à jour.
     * @param city de l'office à mettre à jour.
     * @param phone de l'office à mettre à jour.
     * @param addressLine1 de l'office à mettre à jour.
     * @param addressLine2 de l'office à mettre à jour.
     * @param state de l'office à mettre à jour.
     * @param country de l'office à mettre à jour.
     * @param postal_code de l'office à mettre à jour.
     * @return l'office mise à jour et le status http de la requête.
     */
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Offices> updateOffice(@PathVariable( value = "id" ) int id, String city, String phone,
                                                String addressLine1, String addressLine2, String state,
                                                String country, String postal_code )
    {
        return new ResponseEntity<>( this.officesService.updateOffice(id, city, phone, addressLine1, addressLine2,state,
                country,postal_code), HttpStatus.OK);
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
