package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.exceptions.categorie.CategorieNotFoundException;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service CategoriesService.
 * </p>
 * @author Gaëtan T.
 */
public interface CategoriesService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de toutes les categories.
     *
     * </p>
     * @exception CategorieNotFoundException si catégorie non trouvée.
     */
    List<Categories> getAllCategories() throws CategorieNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get une catégorie par son id.
     *
     * </p>
     * @exception CategorieNotFoundException si catégorie non trouvée.
     * @param id de la catégorie.
     */
    Categories getCategorieById(int id) throws CategorieNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de créer une catégorie par son id et le formulaire.
     *
     * </p>
     * @param id de la catégorie.
     * @param categoryName de la catégorie.
     * @param textDescription de la catégorie.
     * @param htmlDescription de la catégorie.
     * @param image de la catégorie.
     */
    Categories createCategories(int id, String categoryName, String textDescription, String htmlDescription , String image);
}
