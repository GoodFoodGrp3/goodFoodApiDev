package com.goodfood.api.services;

import com.goodfood.api.entities.Comments;
import com.goodfood.api.exceptions.categorie.CategorieNotFoundException;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;

import java.util.List;

/**
 * <p>
 *  Class qui permet de définir le service de l'entité CommentsService.
 * </p>
 * @author Gaëtan T.
 */
public interface CommentsService
{
    /**
     * <p><b>Méthode</b> qui permet de get la liste de tous les commentaires.
     *
     * </p>
     * @exception CommentsNotFoundException si commentaire non trouvé.
     */
    List<Comments> getAllComments() throws CommentsNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un commentaire par son id.
     *
     * </p>
     * @exception CommentsNotFoundException si commentaire non trouvé.
     * @param id du commentaire.
     */
    Comments getCommentById(int id) throws CommentsNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de supprimer un commentaire par son id.
     *
     * </p>
     * @param id du commentaire.
     */
    void deleteCommentById(int id);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un commentaire par son id.
     *
     * </p>
     * @param id du commentaire.
     * @param newContent texte du commentaire qui a changé.
     */
    Comments updateComment(int id, String newContent);
//    Comments createComment(int id,String body);
}