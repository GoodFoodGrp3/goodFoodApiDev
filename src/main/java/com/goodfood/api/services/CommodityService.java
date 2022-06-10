package com.goodfood.api.services;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.commodity.CommodityNotFoundException;

import java.util.List;

/**
 * <p>
 *   Class qui représente le service CommodityService.
 * </p>
 * @author Gaëtan T.
 */
public interface CommodityService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de toutes les matières premières.
     *
     * </p>
     * @exception CommodityNotFoundException si matière première non trouvée.
     * @return liste de matière première.
     */
    List<Commodity> getAllCommoditys() throws CommodityNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get la liste d'une catégorie par son id.
     *
     * </p>
     * @exception CommodityNotFoundException si matière première non trouvée.
     * @param id de la matière première.
     * @return matière première
     */
    Commodity getCommodityById (int id) throws CommodityNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de supprimer une matière première par son id.
     *
     * </p>
     * @param id de la matière première.
     */
    void deleteCommodityById(int id);

    /**
     * <p><b>Méthode</b> qui permet de créer une matière première par son id et un formulaire.
     *
     * </p>
     * @param id de la matière première.
     * @param employeeId d'un employer.
     * @param commodityName de la matière première.
     * @param commodityDescription de la matière première.
     * @param unit de la matière première.
     * @param buyPrice de la matière première.
     * @param vendorProvider de la matière première.
     * @param quantity de la matière première.
     */
    Commodity createCommodities(int id, Provider providerId, Employees employeeId, String commodityName,
                                String commodityDescription, String unit, double buyPrice,
                                String vendorProvider, int quantity);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour une matière première par son id et un formulaire.
     *
     * </p>
     * @param id de la matière première.
     * @param provider numéro fournisseur.
     * @param employees numéro employer.
     * @param commodity_name de la matière première.
     * @param unit de la matière première.
     * @param buy_price de la matière première.
     * @param vendor_provider de la matière première.
     */
    Commodity updateCommodity(int id, int provider, int employees, String commodity_name, String unit,
                              double buy_price, String vendor_provider);
}
