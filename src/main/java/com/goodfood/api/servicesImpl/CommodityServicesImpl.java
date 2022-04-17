package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.repositories.CommodityRepository;
import com.goodfood.api.services.CommodityService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service( value = "CommodityService" )
public class CommodityServicesImpl implements CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    ErrorLogServices errorLogServices;

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

    @Override
    public Commodity updateCommodity(int id, int provider_id, int employee_id, String commodity_name, int quantity_in_stock, double buy_price, String vendor_provider) {
        Commodity commodity = this.commodityRepository.findById( id );
        if ( commodity == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) );
        }
        //commodity.setProvider(provider_id);
        //commodity.setEmployees(employees);
        commodity.setCommodity_name(commodity_name);
        commodity.setQuantity_in_stock(quantity_in_stock);
        commodity.setBuy_price(buy_price);
        commodity.setVendor_provider(vendor_provider);
        commodityRepository.save(commodity);
        return commodity;
    }

    @Override
    public void deleteCommentById(int id) {
        Commodity commodity = this.commodityRepository.findById( id );
        if ( commodity == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id ) );
        }

        this.commodityRepository.deleteById( id );
    }

}
