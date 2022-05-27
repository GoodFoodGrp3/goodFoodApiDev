package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.employee.CreateCategoriesForm;
import com.goodfood.api.services.CategoriesService;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des categories.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200) </p>
 * <p><b>@RestController</b> permet de spécifier que la classe CategoriesController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /categories</p>
 * @author Gaëtan T.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategoriesController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet CategoriesService qui représente la class CategoriesService.
     */
    @Autowired
    private CategoriesService categoriesService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;



    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner toutes les catégories.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal : /categories.</p>
     * @apiNote méthode GET.
     * @return toutes les catégories
     */
    @GetMapping(value = "")
    public List<Categories> getAllCategories()
    {
        return this.categoriesService.getAllCategories();
    }

    /**
     * <p><b>Méthode/Route</b> qui retourne une catégorie selon l'id choisi.
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /categories/{id}.</p>
     * @apiNote méthode GET.
     * @param id l'id de la categorie.
     * @return une catégorie selon l'id choisi.
     */
    @GetMapping( value = "/{id}" )
    public Categories getCategorieById(@PathVariable int id )
    {
        return this.categoriesService.getCategorieById( id );
    }

    // ***************
    // POST/CREATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui retourne une catégorie créer.
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal : /categories.</p>
     * @apiNote méthode POST.
     * @param createCategoriesForm formulaire de création d'une catégorie.
     * @return une catégorie créer.
     */
    @PostMapping( value = "" )
    public Categories createCategories(@RequestBody JwtRequest authenticationRequest, CreateCategoriesForm createCategoriesForm )
    {
        return this.categoriesService.createCategories( createCategoriesForm.getId(),
                createCategoriesForm.getCategoryName(), createCategoriesForm.getTextDescription(),
                createCategoriesForm.getHtmlDescription(), createCategoriesForm.getImage());
    }


    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void generatePrivilegeErrorIf( boolean test )
    {
        if ( test )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities." ) );
            throw new EmployeeStatusException();
        }
    }
}
