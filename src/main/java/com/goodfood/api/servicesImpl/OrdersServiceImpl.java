package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Order_details;
import com.goodfood.api.entities.Orders;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.OrderDetailsRepository;
import com.goodfood.api.repositories.OrdersRepository;
import com.goodfood.api.request.orders.OrderDetailsForm;
import com.goodfood.api.request.orders.OrderTemplateForm;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(value = "OrdersService")
public class OrdersServiceImpl implements OrdersService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    //TODO getCurrentCustomerOrders(int CustomerId)

    /*
    Fonction pour les employes
     */
    @Override
    public List<Orders> getAllOrders() throws OrderNotFoundException
    {
        List<Orders> getAllOrders = (List<Orders>) ordersRepository.findAll();

        if (getAllOrders == null || getAllOrders.isEmpty())
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND, "Aucune commande trouvée"));
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
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND, "La commande n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "La commande n° " + id + " est introuvable");
        }

        else
        {
            return orders;
        }
    }

    @Override
    public List<Order_details> getOneOrderDetails(int orderNumber) {
        List<Order_details> getOrderDetailsOfOrder = orderDetailsRepository.findById(orderNumber);

        if(getOrderDetailsOfOrder == null || getOrderDetailsOfOrder.isEmpty()) {
            errorLogServices.recordLog( new ErrorLog(null, HttpStatus.NOT_FOUND, "Aucune commande trouvée"));
            throw new ProductsNotFoundException("Aucune commande trouvée");
        }

        return getOrderDetailsOfOrder;
    }

    @Override
    public Orders registerNewOrder(OrderTemplateForm newOrder) {
        Orders order = new Orders();
        List<OrderDetailsForm> orderDetailsList = new ArrayList<>();
        List<Order_details> order_detailsList = new ArrayList<>();
        Customers customers = new Customers();

        order.setOrder_date(new Timestamp(System.currentTimeMillis()));
        order.setComments(newOrder.getComments());

        // Call customer profil
        customers.setId(newOrder.getCustomerId());
        order.setCustomers(customers);

        //Generate order id INT
        UUID orderUUID = UUID.randomUUID();
        order.setId(orderUUID.toString());

        order.setStatus(newOrder.getStatus());

        order.setDelivery_date(new Timestamp(System.currentTimeMillis()));
        order.setShipped_date(new Timestamp(System.currentTimeMillis()));

        for (OrderDetailsForm orderDetailsRow : newOrder.getOrderDetails()) {
            orderDetailsList.add(orderDetailsRow);
        }

        for (int i = 0; i < orderDetailsList.size(); i++) {
            order_detailsList.add(new Order_details(orderDetailsList.get(i).getProduct_id(),orderUUID.toString(),
                    orderDetailsList.get(i).getProduct_name(),orderDetailsList.get(i).getCode_tva_id(),
                    orderDetailsList.get(i).getQuantity_ordered(),orderDetailsList.get(i).getPriceEach(),
                    orderDetailsList.get(i).getOrder_line_number()));
        }

        try {
            ordersRepository.save(order);
            orderDetailsRepository.saveAll(order_detailsList);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

        return order;
    }

    @Override
    public OrderTemplateForm getOrderByCustomerId(int id) {
        return null;
    }

}
