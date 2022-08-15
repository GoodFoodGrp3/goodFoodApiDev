package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.*;
import com.goodfood.api.request.commodity.OrderCommodityDetailsForm;
import com.goodfood.api.request.commodity.OrderCommodityForm;
import com.goodfood.api.services.CommodityService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(value = "CommodityService")
public class CommodityServicesImpl implements CommodityService
{
    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommodityOrderRepository commodityOrderRepository;

    @Autowired
    CommodityOrderDetailsRepository commodityOrderDetailsRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    TaxeRepository taxeRepository;

    @Autowired
    EmployeesServiceImpl employeesService;

    @Autowired
    ErrorLogServices errorLogServices;

    Provider provider;

    Taxe taxe;


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
    public Commodity createCommodities(int id, int providerId, int taxe_id,String commodityName,
                                       String commodityDescription, String unit, double buyPrice,
                                       String vendorProvider, int quantity)
    {

        provider = new Provider();

        taxe = new Taxe();

        try
        {
           provider = this.providerRepository.findById(providerId);
           taxe = this.taxeRepository.findById(taxe_id);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        provider.setProvider_id(providerId);
        taxe.setId(taxe_id);

        final Commodity commodity = new Commodity(provider, taxe,commodityName,commodityDescription,
                unit,buyPrice,vendorProvider,quantity);
        try
        {
           this.commodityRepository.save(commodity);
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return commodity;
    }

    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Commodity updateCommodity(int id, int provider_id, int taxe_id, String commodity_name, String commodity_description,
                                     String unit, double buy_price, int quantity)
    {
        provider = new Provider();

        taxe = new Taxe();

        Commodity commodity = this.commodityRepository.findById(id);

        if (commodity == null)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None commodity could be found with the id %d", id));
        }

        provider.setProvider_id(provider_id);
        taxe.setId(taxe_id);

        commodity.setProvider(provider);
        commodity.setTaxe(taxe);
        commodity.setCommodity_name(commodity_name);
        commodity.setCommodity_description(commodity_description);
        commodity.setUnit(unit);
        commodity.setBuy_price(buy_price);
        commodity.setQuantity(quantity);
        commodityRepository.save(commodity);
        return commodity;
    }

    /*
    CREATION NOUVELLE COMMANDE DE MARCHANDISE
     */
    @Override
    public Order_commodity registerCommodityNewOrder(OrderCommodityForm newOrder) {
        Order_commodity order_commodity = new Order_commodity();
        List<OrderCommodityDetailsForm> orderCommodityDetailsFormsList = new ArrayList<>();
        List<Order_commodity_details> order_commodity_detailsList = new ArrayList<>();

        order_commodity.setOrder_date(new Timestamp(System.currentTimeMillis()));
        order_commodity.setDelivery_date(new Timestamp(System.currentTimeMillis()));
        order_commodity.setShipped_date(new Timestamp(System.currentTimeMillis()));

        //Generate order id
        UUID orderUUID = UUID.randomUUID();
        order_commodity.setOrder_commodity_id(orderUUID.toString());

        order_commodity.setStatus(newOrder.getStatus());

        for(OrderCommodityDetailsForm orderDetailsRow : newOrder.getOrderCommodityDetailsForms()){
            orderCommodityDetailsFormsList.add(orderDetailsRow);
        }

        for (int i = 0; i < orderCommodityDetailsFormsList.size(); i++) {
            order_commodity_detailsList.add(new Order_commodity_details(orderCommodityDetailsFormsList.get(i).getProduct_id(), orderUUID.toString(),
                    orderCommodityDetailsFormsList.get(i).getCommodity_name(), orderCommodityDetailsFormsList.get(i).getUnit(), orderCommodityDetailsFormsList.get(i).getCode_tva_id(),
                    orderCommodityDetailsFormsList.get(i).getQuantity_ordered(), orderCommodityDetailsFormsList.get(i).getPriceEach()));
        }

        try {

            order_commodity.setEmployees(employeesService.getEmployeeById(newOrder.getEmployeeId()));
            commodityOrderRepository.save(order_commodity);
            commodityOrderDetailsRepository.saveAll(order_commodity_detailsList);

        } catch (Exception e) {
            e.getMessage();
        }

        return order_commodity;
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
