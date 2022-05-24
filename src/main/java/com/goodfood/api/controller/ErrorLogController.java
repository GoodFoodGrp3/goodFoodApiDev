package com.goodfood.api.controller;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des erreurs de logs.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe ErrorLogController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /admin/errorLogs </p>
 * @author Gaëtan T.
 */
@RestController
@CrossOrigin( "http://localhost:4200" )
@RequestMapping( "/admin/errorLogs" )
public class ErrorLogController
{
    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les erreurs de logs.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /admin/errorLogs.</p>
     * @apiNote méthode GET.
     * @return toutes les erreurs de logs.
     */
    @GetMapping( value = "" )
    public List<ErrorLog> getErrorLogs()
    {
        return errorLogServices.getErrorLogs();
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