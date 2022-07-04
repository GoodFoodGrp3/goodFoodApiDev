package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.CommodityRepository;
import com.goodfood.api.services.CommodityService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service(value = "CommodityService")
public class CommodityServicesImpl implements CommodityService
{
    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Commodity> getAllCommoditys() throws CommodityNotFoundException
    {
        List<Commodity> getAllCommoditys = (List<Commodity>) commodityRepository.findAll();

        if (getAllCommoditys == null || getAllCommoditys.isEmpty())
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND, "Aucune commodity trouvée"));
            throw new ProductsNotFoundException( "Aucune commodity trouvée" );
        }

        return getAllCommoditys;
    }

    @Override
    public Commodity getCommodityById(int id) throws CommodityNotFoundException
    {
        Commodity commodity = commodityRepository.findById(id);

        if(commodity == null)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND, "La commodity n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "La commodity n° " + id + " est introuvable");
        }

        else
        {
            return commodity;
        }
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Commodity createCommodities(int id, Provider providerId, String commodityName,
                                       String commodityDescription, String unit, double buyPrice,
                                       String vendorProvider, int quantity)
    {
        final Commodity commodity = new Commodity(providerId,commodityName,commodityDescription,
                unit,buyPrice,vendorProvider,quantity);

        return this.commodityRepository.save(commodity);
    }

    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Commodity updateCommodity(int id, int provider_id, int employee_id, String commodity_name,
                                     String unit, double buy_price, String vendor_provider)
    {
        Commodity commodity = this.commodityRepository.findById(id);

        if (commodity == null)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id));
        }
        //commodity.setProvider(provider_id);
        //commodity.setEmployees(employees);
        commodity.setCommodity_name(commodity_name);
        commodity.setUnit(unit);
        commodity.setBuy_price(buy_price);
        commodity.setVendor_provider(vendor_provider);
        commodityRepository.save(commodity);
        return commodity;
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteCommodityById(int id)
    {
        Commodity commodity = this.commodityRepository.findById(id);

        if ( commodity == null )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id));
        }

        this.commodityRepository.deleteById(id);
    }


}
