package com.goodfood.api.controller;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Status;
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

@RestController
@RequestMapping("/commoditys")
public class CommodityController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ErrorLogServices errorLogServices;

    //private AuthenticationService authenticationService;

    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Commodity> getAllCommoditys()
    {
        return this.commodityService.getAllCommoditys();
    }

    @GetMapping( value = "/{id}" )
    public Commodity getCommodityById(@PathVariable int id )
    {
        return this.commodityService.getCommodityById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    @PostMapping( value = "" )
    public Commodity createCommoditys(@RequestBody CreateCommoditiesForm createCommoditiesForm )
    {
        /*Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);*/

        return this.commodityService.createCommodities(createCommoditiesForm.getId(),
                createCommoditiesForm.getProviderId(), createCommoditiesForm.getEmployeeId(),
                createCommoditiesForm.getCommodityName(), createCommoditiesForm.getCommodityDescription(),
                createCommoditiesForm.getQuantityinStock(),createCommoditiesForm.getBuyPrice(),
                createCommoditiesForm.getVendorProvider(),createCommoditiesForm.getQuantity());
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Commodity> updateCommodity(@PathVariable( value = "id" ) int id, int provider_id,
                                        int employee_id, String commodity_name, int quantity_in_stock, double buy_price,
                                                                                String vendor_provider, int quantity)
    {
        return new ResponseEntity<>(this.commodityService.updateCommodity( id, provider_id, employee_id,
                            commodity_name, quantity_in_stock,buy_price, vendor_provider), HttpStatus.OK);
    }

    // ***************
    // DELETE
    // ***************

    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete( @PathVariable( value = "id" ) int id )
    {
        /*Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf( status != Status.RESTAURATEUR || status != Status.RESTAURATEUR || status != Status.ADMINISTRATEUR);*/

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
