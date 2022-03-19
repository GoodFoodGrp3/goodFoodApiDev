package com.goodfood.api.controller;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Comments;
import com.goodfood.api.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping(value = "")
    public List<Categories> getAllCategories() {
        return this.categoriesService.getAllCategories();
    }

    @GetMapping( value = "/{id}" )
    public Categories getCategorieById(@PathVariable int id ) {
        return this.categoriesService.getCategorieById( id );
    }
}
