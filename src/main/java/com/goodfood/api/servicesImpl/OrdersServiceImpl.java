package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Orders;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.OrdersRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "OrdersService")
public class OrdersServiceImpl implements OrdersService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Orders> getAllOrders() throws OrderNotFoundException
    {
        List<Orders> getAllOrders = (List<Orders>) ordersRepository.findAll();

        if (getAllOrders == null || getAllOrders.isEmpty())
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "Aucune commande trouvée"));
            throw new ProductsNotFoundException( "Aucune commande trouvée" );
        }

        return getAllOrders;
    }

    @Override
    public Orders getOrderById(int id) throws OrderNotFoundException
    {
        Orders orders = ordersRepository.findById(id);

        if(orders == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "La commande n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "La commande n° " + id + " est introuvable");
        }

        else
        {
            return orders;
        }
    }
}
