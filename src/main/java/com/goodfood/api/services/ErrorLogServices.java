package com.goodfood.api.services;

import com.goodfood.api.entities.ErrorLog;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service ErrorLogServices.
 * </p>
 * @author Gaëtan T.
 */
public interface ErrorLogServices
{
    /**
     * <p><b>Méthode</b> qui permet d'enregistrer les logs d'erreur.
     *
     * </p>
     * @param errorLog entité.
     */
    public void recordLog(ErrorLog errorLog);

    /**
     * <p><b>Méthode</b> qui permet de get la liste entiére des erreurs logs.
     *
     * </p>
     */
    public List<ErrorLog> getErrorLogs();
}
