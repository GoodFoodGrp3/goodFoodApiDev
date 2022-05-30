package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.ConstraintViolationException;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.employee.*;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  Class qui permet de définir toutes les <b>routes</b> des employers.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@RestController</b> permet de spécifier que la classe EmployeesController est un controller</p>
 * <p><b>@RequestMapping</b> permet de spécifier la route principal de la classe est : /employees </p>
 * @author Gaëtan T.
 */
@CrossOrigin( "*" )
@RestController
@RequestMapping("/employees")
public class EmployeesController
{
    // ***************
    // VARIABLE DE CLASSE
    // ***************

    /**
     * Déclaration de l'objet EmployeesService qui représente la class EmployeesService.
     */
    @Autowired
    private EmployeesService employeesService;

    /**
     * Déclaration de l'objet ErrorLogServices qui représente la class ErrorLogServices.
     */
    @Autowired
    private ErrorLogServices errorLogServices;



    // ***************
    // GET
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner tous les employers.
     *
     * </p>
     * <p>La value = "" spécifie que la route est la même que la route principal -> /employees.</p>
     * @return tous les employers.
     */
    @GetMapping(value = "")
    public List<Employees> getAllEmployees()
    {
        return this.employeesService.getAllEmployees();
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner l'employer par son id.
     *
     * </p>
     * <p>La value = "/{id}" spécifie que pour y accéder la route est : /employees/{id}.</p>
     * @param id de l'employee.
     * @return l'employee par son id.
     */
    @GetMapping(value = "/{id}")
    public Employees getEmployeeById(@PathVariable int id)
    {
        return this.employeesService.getEmployeeById( id );
    }


    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le status de l'employer par son nom d'utilisateur.
     *
     * </p>
     * <p>La value = "/status/{username}" spécifie que pour y accéder la route est : /employees/status/{username}.</p>
     * @param username de l'employee.
     * @return le status de l'employer connecté.
     */
    @GetMapping(value = "/status/{username}")
    public String getStatus(@PathVariable String username)
    {
        return this.employeesService.getStatus( username );
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner l'employer par son nom d'utilisateur.
     *
     * </p>
     * <p>La value = "/profile/search/{username}" spécifie que pour y accéder la route est : /employees/profile/search/{username}.</p>
     * @param username de l'employee.
     * @return l'employer par son nom d'utilisateur.
     */
    @GetMapping(value = "/profile/search/{username}")
    public Employees getEmployeeByUsername( @PathVariable String username)
    {
        return employeesService.getEmployeeByUserName(username);
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner l'employer actuellement connecté.
     *
     * </p>
     * <p>La value = "/current" spécifie que pour y accéder la route est : /employees/current.</p>
     * @return l'employer actuellement connecté et le status http de la requête.
     */
    @GetMapping( "/current" )
    public ResponseEntity<Employees> getCurrentEmployee() {
        return new ResponseEntity<>( this.employeesService.getCurrentEmployee(), HttpStatus.OK );
    }



    // ***************
    // POST/REGISTER/LOGIN
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de retourner l'employer venant de s'enregistrer.
     *
     * </p>
     * <p>La value = "/register" spécifie que pour y accéder la route est : /employees/register.</p>
     * @param registerEmployeeForm formulaire de l'enregistrement d'un employer.
     * @return l'employer venant de s'enregistrer et le status http de la requête.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Employees> registerEmployee(@Valid @RequestBody RegisterEmployeeForm registerEmployeeForm)
    {
        //constraintViolationCheck( errors, request );

        return new ResponseEntity<Employees>(employeesService.registerEmployee(registerEmployeeForm), HttpStatus.OK);
    }


    /**
     * <p><b>Méthode/Route</b> qui permet de retourner le mot de passe mise à jour de l'employer.
     *
     * </p>
     * <p>La value = "/profile/{id}/password" spécifie que pour y accéder la route est : /employees/profile/{id}/password.</p>
     * @param id de l'employer.
     * @param updateEmployeePasswordForm formulaire de mise à jour du mot de passe.
     * @return l'employer venant de s'enregistrer et le status http de la requête.
     */
    @PutMapping(value = "/profile/{id}/password")
    public LoginDao updateEmployeePassword(@PathVariable int id,
                                           @RequestBody UpdateUserPasswordForm updateEmployeePasswordForm)
    {

        return employeesService.updatePassword(id, updateEmployeePasswordForm);
    }

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour l'employer par son id.
     *
     * </p>
     * <p>La value = "/profile/{id}" spécifie que pour y accéder la route est : /employees/profile/{id}.</p>
     * @param id de l'employer.
     * @param updateEmployeeForm formulaire de mise à jour du mot de l'employer.
     * @return l'id de l'employer et les nouvelles informations de l'employer.
     */
    @PutMapping(value = "/profile/{id}")
    public Employees updateEmployeeById(@PathVariable int id, @Valid @RequestBody UpdateEmployeeForm updateEmployeeForm)
    {
        //constraintViolationCheck( errors, request )
        return employeesService.updateEmployeeProfile(id, updateEmployeeForm);

    }

    /**
     * <p><b>Méthode/Route</b> qui permet de mettre à jour le status de l'employer.
     *
     * </p>
     * <p>La value = "/admin/{id}/status" spécifie que pour y accéder la route est : /employees/admin/{id}/status.</p>
     * @param id de l'employer.
     * @param updateEmployeeStatusForm formulaire de mise à jour du status de l'employer.
     * @return l'id de l'employer et le nouveau status de l'employer.
     */
    @PutMapping( value = "/admin/{id}/status" )
    public LoginDao updateEmployeesStatus(@PathVariable int id,
                                          @RequestBody UpdateEmployeeStatusForm updateEmployeeStatusForm)
    {
        return employeesService.updateStatus(id, updateEmployeeStatusForm);
    }

    // ***************
    // DELETE
    // ***************

    /**
     * <p><b>Méthode/Route</b> qui permet de supprimer un employer par son id.
     *
     * </p>
     * <p>La value = "/profile/{id}" spécifie que pour y accéder la route est : /employees/profile/{id}.</p>
     * @param id de l'employer à supprimer.
     */
    @DeleteMapping(value = "/profile/{id}")
    public void deleteEmployeeById(@PathVariable int id)
    {
        this.employeesService.deleteById(id);
    }

    // ***************
    // ERROR MANAGEMENT
    // ***************

    private void constraintViolationCheck(Errors errors, HttpServletRequest request)
    {
        if (errors.hasErrors())
        {
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();

            for (ObjectError e : errors.getAllErrors())
            {
                violationsList.add(e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";

            for (ConstraintViolation<?> violation : violationsList)
            {
                if (violationsList.indexOf(violation) > 0)
                {
                    exceptionMessage += " | ";
                }

                exceptionMessage += violation.getMessage();
            }
            errorLogServices
                    .recordLog(new ErrorLog(request.getHeader("Host"), HttpStatus.BAD_REQUEST, exceptionMessage));
            throw new ConstraintViolationException(exceptionMessage);
        }
    }

    private void generatePrivilegeErrorIf(boolean test)
    {
        if (test)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.FORBIDDEN,
                    "You have not the right authorities."));
            throw new EmployeeStatusException();
        }
    }
}
