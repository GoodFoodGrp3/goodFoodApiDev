package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.repositories.CommodityRepository;
import com.goodfood.api.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "CommodityService" )
public class CommodityServicesImpl implements CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    @Override
    public List<Commodity> getAllCommoditys() {
        return (List<Commodity>) this.commodityRepository.findAll();
    }

    @Override
    public Commodity getCommodityById(int id) {
        return this.commodityRepository.findById(id);
    }

    @Override
    public Commodity createCommodities(int id, Provider providerId, Employees employeeId, String commodityName, String commodityDescription, int quantityinStock, double buyPrice, String vendorProvider, int quantity) {
        final Commodity commodity = new Commodity(providerId,employeeId,commodityName,commodityDescription,quantityinStock,buyPrice,vendorProvider,quantity);

        return this.commodityRepository.save(commodity);
    }


}
