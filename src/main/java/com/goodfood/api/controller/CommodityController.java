package com.goodfood.api.controller;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Commodity;
import com.goodfood.api.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/commoditys")
    public List<Commodity> getAllCommoditys(){
        return commodityService.getAllCommoditys();
    }
}
