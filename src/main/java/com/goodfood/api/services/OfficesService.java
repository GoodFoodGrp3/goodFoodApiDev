package com.goodfood.api.services;

import com.goodfood.api.entities.Offices;
import com.goodfood.api.exceptions.offices.OfficesNotFoundException;

import java.util.List;


/**
 * <p>
 *  Class qui représente le service OfficesService.
 * </p>
 * @author Gaëtan T.
 */
public interface OfficesService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de tous les offices.
     *
     * </p>
     * @exception OfficesNotFoundException si office non trouvé.
     */
    List<Offices> getAllOffices() throws OfficesNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un office par son id.
     *
     * </p>
     * @exception OfficesNotFoundException si office non trouvé.
     * @param id de l'office.
     */
    Offices getOfficeById(int id) throws OfficesNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de créer un office par son id.
     *
     * </p>
     * @param id de l'office.
     * @param phone de l'office.
     * @param addressline1 de l'office.
     * @param addressline2 de l'office.
     * @param state de l'office.
     * @param country de l'office.
     * @param postal_code de l'office.
     */
    Offices createOffices(int id, String name, String city, String phone, String addressline1 , String addressline2, String state,
                          String country, String postal_code);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un office par son id.
     *
     * </p>
     * @param id de l'office.
     * @param city de l'office.
     * @param phone de l'office.
     * @param addressLine1 de l'office.
     * @param addressLine2 de l'office.
     * @param state de l'office.
     * @param country de l'office.
     * @param postal_code de l'office.
     */
    Offices updateOffice(int id, String name, String city, String phone, String addressLine1, String addressLine2, String state,
                         String country, String postal_code);
}
