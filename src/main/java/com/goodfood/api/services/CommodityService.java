package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.entities.Commodity;
import com.goodfood.api.repositories.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    public List<Commodity> getAllCommoditys() {
        return (List<Commodity>) commodityRepository.findAll();
    }
}
