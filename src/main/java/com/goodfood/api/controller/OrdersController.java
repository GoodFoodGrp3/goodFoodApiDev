package com.goodfood.api.controller;

import com.goodfood.api.entities.Orders;
import com.goodfood.api.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des commandes.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe OrdersController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /orders </p>
 * @author Gaëtan T.
 */
@RestController
@CrossOrigin( "*" )
@RequestMapping("/orders")
public class OrdersController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet OrdersService qui représente la class OrdersService.
     */
    @Autowired
    private OrdersService ordersService;


    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner toutes les commandes.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /orders.</p>
     * @return toutes les commandes.
     */
    @GetMapping(value = "")
    public List<Orders> getAllOrders()
    {
        return this.ordersService.getAllOrders();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner une commande par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /orders/{id}.</p>
     * @param id de la commande.
     * @return une commande par son id.
     */
    @GetMapping(value = "/{id}")
    public Orders getOrderById(@PathVariable int id )
    {
        return this.ordersService.getOrderById(id);
    }
}
