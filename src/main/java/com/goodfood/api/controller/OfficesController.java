package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.services.CommentsService;
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
}
