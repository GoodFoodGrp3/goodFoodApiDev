package com.goodfood.api.services;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Comments;

import java.util.List;

public interface CategoriesService {

    List<Categories> getAllCategories();
    Categories getCategorieById(int id );
}
