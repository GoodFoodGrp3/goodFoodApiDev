package com.goodfood.api.controller;

import com.goodfood.api.entities.Offices;
import com.goodfood.api.request.employee.CreateOfficesForm;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/offices")
public class OfficesController {

    @Autowired
    private OfficesService officesService;

    @GetMapping(value = "")
    public List<Offices> getAll(){
        return this.officesService.getAllOffices();
    }

    @GetMapping( value = "/{id}" )
    public Offices getOfficeById( @PathVariable int id ) {
        return this.officesService.getOfficeById( id );
    }

    @PostMapping( value = "" )
    public Offices createOffices(@RequestBody CreateOfficesForm createOfficesForm ) {
        return this.officesService.createOffices( createOfficesForm.getId(), createOfficesForm.getCity(),
                createOfficesForm.getPhone(), createOfficesForm.getAddressline1(),
                createOfficesForm.getAddressline2(), createOfficesForm.getState(),
                createOfficesForm.getCountry(),createOfficesForm.getPostal_code());
    }
}
