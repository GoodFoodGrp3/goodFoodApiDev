package com.goodfood.api.repositories;

import com.goodfood.api.entities.Categories;
import org.springframework.data.repository.CrudRepository;


/**
 * <p>
 *  Class qui permet de définir le repository de l'entité Categories.
 * </p>
 * @author Gaëtan T.
 */
public interface CategoriesRepository extends CrudRepository<Categories,Integer>
{
    /**
     * <p><b>Méthode</b> qui permet de chercher une catégorie par son id.
     *
     * </p>
     * @param id de la catégorie.
     */
    Categories findById(int id);
}
