package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.Error_log;
import com.goodfood.api.exceptions.categorie.CategorieNotFoundException;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.CategoriesRepository;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "CategoriesService")
public class CategoriesServiceImpl implements CategoriesService
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Categories> getAllCategories() throws CategorieNotFoundException
    {
        List<Categories> getAllCategories = (List<Categories>) categoriesRepository.findAll();

        if (getAllCategories == null || getAllCategories.isEmpty())
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "Aucune categorie trouvée"));
            throw new ProductsNotFoundException( "Aucune categorie trouvée" );
        }

        return getAllCategories;
    }

    @Override
    public Categories getCategorieById(int id) throws CategorieNotFoundException
    {
        Categories categories = categoriesRepository.findById(id);

        if(categories == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "La categorie n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "La catégorie n° " + id + " est introuvable");
        }

        else
        {
            return categories;
        }
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Categories createCategories(int id, String categoryName, String textDescription, String htmlDescription, String image)
    {
        final Categories categories = new Categories(categoryName,textDescription,htmlDescription,image);

        return this.categoriesRepository.save(categories);
    }
}
