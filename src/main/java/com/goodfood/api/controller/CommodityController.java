package com.goodfood.api.controller;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.request.employee.CreateCommoditiesForm;
import com.goodfood.api.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commoditys")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "")
    public List<Commodity> getAllCommoditys(){
        return this.commodityService.getAllCommoditys();
    }

    @GetMapping( value = "/{id}" )
    public Commodity getCommodityById(@PathVariable int id ) {
        return this.commodityService.getCommodityById( id );
    }

    @PostMapping( value = "" )
    public Commodity createCommoditys(@RequestBody CreateCommoditiesForm createCommoditiesForm ) {
        return this.commodityService.createCommodities( createCommoditiesForm.getId(), createCommoditiesForm.getProviderId(), createCommoditiesForm.getEmployeeId(), createCommoditiesForm.getCommodityName(), createCommoditiesForm.getCommodityDescription(), createCommoditiesForm.getQuantityinStock(),createCommoditiesForm.getBuyPrice(),createCommoditiesForm.getVendorProvider(),createCommoditiesForm.getQuantity());
    }
}
