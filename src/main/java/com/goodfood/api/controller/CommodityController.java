package com.goodfood.api.controller;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.employee.CreateCommoditiesForm;
import com.goodfood.api.services.CommodityService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des matières premières.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe CommodityController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /commoditys </p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@RestController
@RequestMapping("/commoditys")
public class CommodityController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet CommodityService qui représente la class CommodityService.
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner toutes les matières premières.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /commoditys.</p>
     * @apiNote méthode GET.
     * @return toutes les matières premières.
     */
    @GetMapping(value = "")
    public List<Commodity> getAllCommoditys()
    {
        return this.commodityService.getAllCommoditys();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner une matière première par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /commoditys/{id}.</p>
     * @apiNote méthode GET.
     * @param id de la matière première
     * @return une matière première par son id.
     */
    @GetMapping( value = "/{id}" )
    public Commodity getCommodityById(@PathVariable int id )
    {
        return this.commodityService.getCommodityById(id);
    }


    // ***************
    // POST/CREATE
    // ***************


    /**
     * <p><b>Méthode/Route</b> qui permet de retourner la création d'une matière première par le formulaire.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /commoditys.</p>
     * @apiNote méthode POST.
     * @param createCommoditiesForm formulaire pour créer une matière première.
     * @return la création d'une matière première.
     */
    @PostMapping( value = "" )
    public Commodity createCommoditys(@RequestBody CreateCommoditiesForm createCommoditiesForm )
    {
        return this.commodityService.createCommodities(createCommoditiesForm.getId(),
                createCommoditiesForm.getProviderId(), createCommoditiesForm.getEmployeeId(),
                createCommoditiesForm.getCommodityName(), createCommoditiesForm.getCommodityDescription(),
                createCommoditiesForm.getUnit(),createCommoditiesForm.getBuyPrice(),
                createCommoditiesForm.getVendorProvider(),createCommoditiesForm.getQuantity());
    }


    // ***************
    // PUT/UPDATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner la mise à jour d'une matière première par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /commoditys/{id}.</p>
     * @apiNote méthode PUT.
     * @param id de la matière première
     * @return la création d'une matière première.
     */
    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Commodity> updateCommodity(@PathVariable( value = "id" ) int id, int provider_id,
                                        int employee_id, String commodity_name, String unit, double buy_price,
                                                                                String vendor_provider, int quantity)
    {
        return new ResponseEntity<>(this.commodityService.updateCommodity( id, provider_id, employee_id,
                            commodity_name, unit,buy_price, vendor_provider), HttpStatus.OK);
    }

    // ***************
    // DELETE
    // ***************


    /**
     * <p><b>Méthode/Route</b> qui permet de supprimer une matière première par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /commoditys/{id}.</p>
     * @apiNote méthode DELETE.
     * @param id de la matière première à supprimer.
     */
    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete( @PathVariable( value = "id" ) int id )
    {
        this.commodityService.deleteCommodityById(id);
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
