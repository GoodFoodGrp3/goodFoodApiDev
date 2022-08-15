package com.goodfood.api.servicesImpl;

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
import java.util.Optional;
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

    @Autowired
    CustomersServicesImpl customersServices;


    // ***************
    // GET
    // ***************

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
    public Optional<Orders> getOrderById(String id) throws OrderNotFoundException
    {
        Optional<Orders> orders = ordersRepository.findById(id);

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
    public OrderTemplateForm getOneOrderDetails(String orderNumber) {

        Optional<Orders> ordersTMP = ordersRepository.findById(orderNumber);

        List<Order_details> oneOrderDetailsOfOrder = orderDetailsRepository.findById(orderNumber);

        List<OrderDetailsForm> oneOrderDetailsForm = new ArrayList<>();

        if(oneOrderDetailsOfOrder == null || oneOrderDetailsOfOrder.isEmpty()) {
            errorLogServices.recordLog( new ErrorLog(null, HttpStatus.NOT_FOUND, "Aucune commande trouvée"));
            throw new OrderNotFoundException("Aucune commande trouvée");
        }

        for (int i = 0; i < oneOrderDetailsOfOrder.size(); i++) {
            oneOrderDetailsForm.add(new OrderDetailsForm(oneOrderDetailsOfOrder.get(i).getProduct_id(), oneOrderDetailsOfOrder.get(i).getProduct_name(),
                    oneOrderDetailsOfOrder.get(i).getCode_tva_id(), oneOrderDetailsOfOrder.get(i).getQuantity_ordered(), oneOrderDetailsOfOrder.get(i).getPriceEach()));
        }

        OrderTemplateForm finalOrder = new OrderTemplateForm(ordersTMP.get().getId(), ordersTMP.get().getCustomers().getId(),ordersTMP.get().getStatus(), ordersTMP.get().getComments(),oneOrderDetailsForm);
        return finalOrder;
    }

    @Override
    public Orders registerNewOrder(OrderTemplateForm newOrder) {
        Orders order = new Orders();
        List<OrderDetailsForm> orderDetailsList = new ArrayList<>();
        List<Order_details> order_detailsList = new ArrayList<>();

        order.setOrder_date(new Timestamp(System.currentTimeMillis()));
        order.setDelivery_date(new Timestamp(System.currentTimeMillis()));
        order.setShipped_date(new Timestamp(System.currentTimeMillis()));
        order.setComments(newOrder.getComments());

        //Generate order id
        UUID orderUUID = UUID.randomUUID();
        order.setId(orderUUID.toString());

        order.setStatus(newOrder.getStatus());

        for (OrderDetailsForm orderDetailsRow : newOrder.getOrderDetails()) {
            orderDetailsList.add(orderDetailsRow);
        }

        for (int i = 0; i < orderDetailsList.size(); i++) {
            order_detailsList.add(new Order_details(orderDetailsList.get(i).getProduct_id(),orderUUID.toString(),
                    orderDetailsList.get(i).getProduct_name(),orderDetailsList.get(i).getCode_tva_id(),
                    orderDetailsList.get(i).getQuantity_ordered(),orderDetailsList.get(i).getPriceEach()));
        }

        try {

            // Call customer profil
            order.setCustomers(customersServices.getCustomerById(newOrder.getCustomerId()));
            ordersRepository.save(order);
            orderDetailsRepository.saveAll(order_detailsList);
        } catch (Exception e) {
            e.getMessage();
        }

        return order;
    }

    @Override
    public List<Orders> getOrderByCustomerId(int id) {

        List<Orders> getAllOrdersByCustomerId = ordersRepository.findByCustomers(id);

        if (getAllOrdersByCustomerId == null || getAllOrdersByCustomerId.isEmpty()){
            errorLogServices.recordLog( new ErrorLog(null, HttpStatus.NOT_FOUND, "Aucune commande trouvée"));
            throw new OrderNotFoundException( "Aucune commande trouvée" );
        }
        else {
            return getAllOrdersByCustomerId;
        }
    }

}
