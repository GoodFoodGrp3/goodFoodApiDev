package com.goodfood.api.services;

import com.goodfood.api.entities.Customers;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;
import com.goodfood.api.exceptions.customers.CustomersNotFoundException;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;


import java.util.List;

/**
 * <p>
 *  Class qui représente le service CustomersService.
 * </p>
 * @author Gaëtan T.
 */
public interface CustomersService
{
    /**
     * <p><b>Méthode</b> qui permet de s'enregistrer en tant que client.
     *
     * </p>
     * @param registerCustomerForm formulaire d'enregistrement.
     */
    Customers registerCustomer(RegisterCustomerForm registerCustomerForm);

    /**
     * <p><b>Méthode</b> qui permet de get la liste de tous les clients.
     *
     * </p>
     * @exception CustomersNotFoundException si client non trouvé.
     */
    List<Customers> getAllCustomers() throws CustomersNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un client par son id.
     *
     * </p>
     * @exception CustomersNotFoundException si client non trouvé.
     * @param id du client.
     */
    Customers getCustomerById(int id) throws CustomersNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un client par son nom d'utilisateur.
     *
     * </p>
     * @param email du client.
     */
    Customers getCustomerByEmail(String email);

    /**
     * <p><b>Méthode</b> qui permet de supprimer un client par son id.
     *
     * </p>
     * @param id du client.
     */
    void deleteById(int id);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un client par son id et le formulaire updatecustomerform.
     *
     * </p>
     * @param id du client.
     * @param updateCustomerForm formulaire de mise à jour.
     */
    Customers updateCustomerProfile(int id, UpdateCustomerForm updateCustomerForm);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour le mot de passe par son id et le formulaire updateEmployeePasswordForm.
     *
     * </p>
     * @param id du client.
     * @param updateEmployeePasswordForm formulaire de mise à jour du mot de passe.
     */
    LoginDao updatePassword(int id, UpdateUserPasswordForm updateEmployeePasswordForm);

    LoginDao getCustomerByCustomerId(int id);

    /**
     * <p><b>Méthode</b> qui permet de get à jour le client actuellement connecté.
     *
     * </p>
     */
    Customers getCurrentCustomer();

    /**
     * <p><b>Méthode</b> qui permet de get le status du client par son nom d'utilisateur.
     *
     * </p>
     * @param username du client.
     */
    String getStatus(String username);
}
