package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.repositories.CategoriesRepository;
import com.goodfood.api.services.AuthenticationService;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service(value = "CategoriesService")
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public List<Categories> getAllCategories() {
        return (List<Categories>) this.categoriesRepository.findAll();
    }

    @Override
    public Categories getCategorieById(int id) {
        return this.categoriesRepository.findById(id);
    }

    @Override
    public Categories createCategories(int id, String categoryName, String textDescription, String htmlDescription, String image) {
        final Categories categories = new Categories(categoryName,textDescription,htmlDescription,image);

        return this.categoriesRepository.save(categories);
    }

    @Override
    public void deleteCategoriesById(int id) {

        Categories categories = this.categoriesRepository.findById( id );
        if ( categories == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None Comment could be found with the id %d", id ) );
        }

        this.categoriesRepository.deleteById( id );
    }


}
