package com.goodfood.api.repositories;

import com.goodfood.api.entities.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Class qui permet de définir le repository de l'entité Commodity.
 * </p>
 * @author Gaëtan T.
 */
@Repository
public interface CommodityRepository extends CrudRepository<Commodity,Integer>
{
    /**
     * <p><b>Méthode</b> qui permet de chercher une catégorie par son id.
     *
     * </p>
     * @param id du commentaire.
     */
    Commodity findById(int id);
}
