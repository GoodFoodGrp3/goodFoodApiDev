package com.goodfood.api.controller;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
