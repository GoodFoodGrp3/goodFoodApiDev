package com.goodfood.api.services;

import com.goodfood.api.entities.Order_details;
import com.goodfood.api.entities.Orders;
import com.goodfood.api.exceptions.offices.OfficesNotFoundException;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;
import com.goodfood.api.request.orders.OrderTemplateForm;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service OrdersService.
 * </p>
 * @author Gaëtan T.
 */
public interface OrdersService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de toutes les commandes.
     *
     * </p>
     * @exception OrderNotFoundException si commande non trouvée.
     */
    List<Orders> getAllOrders() throws OrderNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get une commande par son id.
     *
     * </p>
     * @exception OrderNotFoundException si commande non trouvée.
     * @param id de la commande.
     */
    Orders getOrderById(int id) throws OrderNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get une le détails d'une commande par son id.
     *
     * </p>
     * @exception OrderNotFoundException si commande non trouvée.
     * @param orderNumber de la commande.
     */
    List<Order_details> getOneOrderDetails(int orderNumber);

    /**
     * <p><b>Méthode</b> qui permet d'enregistrer une nouvelle commande.
     *
     * </p>
     * @param newOrder formulaire de commande.
     */
    Orders registerNewOrder(OrderTemplateForm newOrder);
}
