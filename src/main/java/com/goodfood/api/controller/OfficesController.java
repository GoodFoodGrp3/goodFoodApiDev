package com.goodfood.api.controller;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.entities.Status;
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

@CrossOrigin( "*" )
@RestController
@RequestMapping("/offices")
public class OfficesController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    private OfficesService officesService;

    @Autowired
    private ErrorLogServices errorLogServices;

    // ***************
    // GET
    // ***************

    @GetMapping(value = "")
    public List<Offices> getAll()
    {
        return this.officesService.getAllOffices();
    }

    @GetMapping(value = "/{id}")
    public Offices getOfficeById(@PathVariable int id)
    {
        return this.officesService.getOfficeById(id);
    }


    // ***************
    // POST/CREATE
    // ***************

    @PostMapping(value = "")
    public Offices createOffices(@RequestBody CreateOfficesForm createOfficesForm)
    {
        /*Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);*/

        return this.officesService.createOffices(createOfficesForm.getId(), createOfficesForm.getCity(),
                createOfficesForm.getPhone(), createOfficesForm.getAddressline1(),
                createOfficesForm.getAddressline2(), createOfficesForm.getState(),
                createOfficesForm.getCountry(),createOfficesForm.getPostal_code());
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Offices> updateOffice(@PathVariable( value = "id" ) int id, String city, String phone,
                                                String addressLine1, String addressLine2, String state,
                                                String country, String postal_code )
    {
       /* Status status = authenticationService.getCurrentEmployee().getStatus();
        generatePrivilegeErrorIf(status != Status.RESTAURATEUR && status != Status.EMPLOYEE && status != Status.ADMINISTRATEUR);*/

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
