package com.goodfood.api.repositories;

import com.goodfood.api.entities.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Class qui permet de définir le repository de l'entité Comments.
 * </p>
 * @author Gaëtan T.
 */
@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer>
{
    /**
     * <p><b>Méthode</b> qui permet de chercher une catégorie par son id.
     *
     * </p>
     * @param id du commentaire.
     */
    Comments findById(int id);
}