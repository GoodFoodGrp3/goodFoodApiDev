package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.repositories.CategoriesRepository;
import com.goodfood.api.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "CategoriesService")
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> getAllCategories() {
        return (List<Categories>) this.categoriesRepository.findAll();
    }

    @Override
    public Categories getCategorieById(int id) {
        return this.categoriesRepository.findById(id);
    }
}
