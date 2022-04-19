package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.exceptions.categorie.CategorieNotFoundException;

import java.util.List;

public interface CategoriesService
{
    List<Categories> getAllCategories() throws CategorieNotFoundException;
    Categories getCategorieById(int id) throws CategorieNotFoundException;
    Categories createCategories(int id, String categoryName, String textDescription, String htmlDescription , String image);
}
