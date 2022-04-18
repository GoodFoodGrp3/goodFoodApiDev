package com.goodfood.api.repositories;

import com.goodfood.api.entities.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories,Integer>
{
    Categories findById(int id);
}
