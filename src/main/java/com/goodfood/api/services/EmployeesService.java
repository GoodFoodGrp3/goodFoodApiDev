package com.goodfood.api.services;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.exceptions.employees.EmployeesNotFoundException;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.employee.RegisterEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeeStatusForm;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service EmployeesService.
 * </p>
 * @author Gaëtan T.
 */
public interface EmployeesService
{
    /**
     * <p><b>Méthode</b> qui permet de s'enregistrer en tant qu'employer.
     *
     * </p>
     * @param  registerEmployeeForm formulaire d'enregistrement.
     */
    Employees registerEmployee(RegisterEmployeeForm registerEmployeeForm);

    Employees getEmployeesByFirstName(String username);

    /**
     * <p><b>Méthode</b> qui permet de get la liste entière de tous les employers.
     *
     * </p>
     * @exception EmployeesNotFoundException si employer non trouvé.
     */
    List<Employees> getAllEmployees() throws EmployeesNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un employer par son id.
     *
     * </p>
     * @exception EmployeesNotFoundException si employer non trouvé.
     * @param id de l'employer.
     */
    Employees getEmployeeById(int id) throws EmployeesNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un employer par son nom d'utilisateur.
     *
     * </p>
     * @param email de l'employer.
     */
    Employees getEmployeeByEmail(String email);

    /**
     * <p><b>Méthode</b> qui permet de supprimer un employer par son id.
     *
     * </p>
     * @param id de l'employer.
     */
    void deleteById(int id);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un employer par son id et le formulaire updateEmployeeForm.
     *
     * </p>
     * @param id de l'employer.
     * @param updateEmployeeForm formulaire de mise à jour.
     */
    Employees updateEmployeeProfile(int id, UpdateEmployeeForm updateEmployeeForm);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour le status d'un employer.
     *
     * </p>
     * @param id de l'employer.
     * @param updateEmployeeStatusForm formulaire de mise à jour du status.
     */
    LoginDao updateStatus(int id, UpdateEmployeeStatusForm updateEmployeeStatusForm);

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour le mot de passe d'un employer.
     *
     * </p>
     * @param id de l'employer.
     * @param updateEmployeePasswordForm formulaire de mise à jour du mot de passe.
     */
    LoginDao updatePassword(int id, UpdateUserPasswordForm updateEmployeePasswordForm);
    LoginDao getEmployeeByEmployeeId(int id);

    /**
     * <p><b>Méthode</b> qui permet de get le status de l'employer.
     *
     * </p>
     * @param username de l'employer.
     */
    String getStatus(String username);

    /**
     * <p><b>Méthode</b> qui permet de get l"employer actuellement connecté.
     *
     * </p>
     */
    Employees getCurrentEmployee();
}
