package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des commentaires.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe CommentsController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /comments </p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@RestController
@RequestMapping("/comments")
public class CommentsController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet CommentsService qui représente la class CommentsService.
     */
    @Autowired
    private CommentsService commentsService;


    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les commentaires.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /comments.</p>
     * @apiNote méthode GET.
     * @return tous les commentaires.
     */
    @GetMapping(value = "")
    public List<Comments> getAll()
    {
        return this.commentsService.getAllComments();
    }


    /**
     * <p><b>Méthode/Route</b> qui retourne un commentaire par l'id.
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /comments/{id}.</p>
     * @apiNote méthode GET.
     * @param id du commentaire.
     * @return un commentaire.
     */
    @GetMapping( value = "/{id}" )
    public Comments getCommentById( @PathVariable int id )
    {
        return this.commentsService.getCommentById( id );
    }


    // ***************
    // POST/CREATE
    // ***************

//    @PostMapping( value = "" )
//    public Comments createComment( @RequestBody CreateCommentForm createCommentForm )
//    {
//        return this.commentsService.createComment( createCommentForm.getId(),createCommentForm.getContent());
//    }

    // ***************
    // DELETE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de supprimer un commentaire par son id.
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /comments/{id}.</p>
     * @apiNote méthode DELETE.
     * @param id du commentaire.
     */
    @DeleteMapping( value = "/{id}" )
    @Transactional
    public void delete(@PathVariable( value = "id" ) int id )
    {
        this.commentsService.deleteCommentById( id );
    }


    // ***************
    // UPDATE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour un commentaire par son id.
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /comments/{id}.</p>
     * @apiNote méthode PUT.
     * @param id du commentaire.
     * @return le commentaire mise à jour et le status http de la requête.
     */
    @PutMapping( value = "/{id}" )
    @Transactional
    public ResponseEntity<Comments> updateComment(@PathVariable( value = "id" ) int id, String newContent )
    {
        return new ResponseEntity<>( this.commentsService.updateComment( id, newContent), HttpStatus.OK );
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

}