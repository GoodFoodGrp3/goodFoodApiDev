package com.goodfood.api.controller;

import com.goodfood.api.entities.Offices;
import com.goodfood.api.entities.Products;
import com.goodfood.api.request.employee.CreateOfficesForm;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Offices> updateOffice(@PathVariable( value = "id" ) int id, String city, String phone, String addressLine1, String addressLine2, String state, String country, String postal_code ) {
        return new ResponseEntity<>( this.officesService.updateProvider( id, city, phone, addressLine1, addressLine2,state,country,postal_code), HttpStatus.OK );
    }
}
