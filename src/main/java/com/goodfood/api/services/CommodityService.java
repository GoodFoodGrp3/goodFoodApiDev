package com.goodfood.api.services;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;

import java.util.List;

public interface CommodityService
{
    List<Commodity> getAllCommoditys() throws CommodityNotFoundException;
    Commodity getCommodityById (int id) throws CommodityNotFoundException;
    void deleteCommodityById(int id);

    Commodity createCommodities(int id, Provider providerId, Employees employeeId, String commodityName,
                                String commodityDescription, int quantityinStock, double buyPrice,
                                String vendorProvider, int quantity);

    Commodity updateCommodity(int id, int provider, int employees, String commodity_name, int quantity_in_stock,
                              double buy_price, String vendor_provider);
}
