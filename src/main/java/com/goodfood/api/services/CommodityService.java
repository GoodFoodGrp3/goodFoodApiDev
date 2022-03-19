package com.goodfood.api.services;

import com.goodfood.api.entities.Commodity;

import java.util.List;

public interface CommodityService {

    List<Commodity> getAllCommoditys();

    Commodity getCommodityById (int id);
}
