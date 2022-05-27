package com.goodfood.api.services;

import com.goodfood.api.entities.Orders;
import com.goodfood.api.exceptions.offices.OfficesNotFoundException;
import com.goodfood.api.exceptions.orders.OrderNotFoundException;

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
}
