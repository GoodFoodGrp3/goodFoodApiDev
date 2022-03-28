package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;

import java.util.List;

public interface CategoriesService {

    List<Categories> getAllCategories();
    Categories getCategorieById(int id );
    Categories createCategories( int id, String categoryName, String textDescription, String htmlDescription , String image );
}
