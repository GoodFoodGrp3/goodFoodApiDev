package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.customers.CustomersNotFoundException;
import com.goodfood.api.exceptions.employees.EmployeeValidationException;
import com.goodfood.api.exceptions.employees.EmployeesNotFoundException;
import com.goodfood.api.repositories.EmployeesRepository;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.employee.RegisterEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeeForm;
import com.goodfood.api.request.employee.UpdateEmployeePasswordForm;
import com.goodfood.api.request.employee.UpdateEmployeeStatusForm;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xin.altitude.cms.common.util.SpringUtils;

import java.util.Collections;
import java.util.List;


@Service( value = "EmployeesService" )
public class EmployeesServiceImpl implements EmployeesService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    private LoginRepository loginRepository;


    private BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return SpringUtils.getBean(BCryptPasswordEncoder.class);
    }


    // ***************
    // CONSTRUCTOR
    // ***************

    public EmployeesServiceImpl()
    {

    }


    // ***************
    // PUT/REGISTER
    // ***************

    @Override
    public Employees registerEmployee(RegisterEmployeeForm registerEmployeeForm)
    {
        Employees employees = new Employees();

        Offices offices = new Offices();

        Order_commodity order_commodity = new Order_commodity();

        LoginDao loginEntity = new LoginDao();

        // validation des attributs
        validationEmail(registerEmployeeForm.getEmail());
        employees.setEmail(registerEmployeeForm.getEmail());

        validationUsername(registerEmployeeForm.getUsername());
        employees.setFirstname(registerEmployeeForm.getUsername());

        employees.setLastname(registerEmployeeForm.getLastname());

        offices.setId(registerEmployeeForm.getSuccursale());
        employees.setOffice_id(offices);

        employees.setOrder_commodity(Collections.singleton(order_commodity));
        loginEntity.setStatus(registerEmployeeForm.getStatus());

        loginEntity.setLogin(registerEmployeeForm.getUsername());

        validationPasswords(registerEmployeeForm.getPassword(), registerEmployeeForm.getCpassword());
        loginEntity.setPassword(this.getBCryptPasswordEncoder().encode(registerEmployeeForm.getPassword()));

        try
        {
            // save in database
            employeesRepository.save(employees);
            employeesRepository.findByFirstname(employees.getFirstname());
            loginEntity.setEmployeeNumber(employees);
            loginRepository.save(loginEntity);
        }

        catch (Exception e)
        {
            e.getMessage();
            return null;
        }



        return employees;
    }

    // ***************
    // GET
    // ***************

    @Override
    public Employees getEmployeesByFirstName(String username)
    {
        return this.employeesRepository.findByFirstname(username);
    }

    @Override
    public List<Employees> getAllEmployees() throws EmployeesNotFoundException
    {
        List<Employees> employees = (List<Employees>) employeesRepository.findAll();

        if (employees == null || employees.isEmpty())
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "Aucun employee trouvé"));
            throw new EmployeesNotFoundException( "Aucun employee trouvé" );
        }

        else
        {
            return employees;
        }
    }

    @Override
    public Employees getEmployeeById(int id) throws EmployeesNotFoundException
    {
        Employees employees = employeesRepository.findById(id);

        if (employees == null)
        {
            errorLogServices
                    .recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "L'employee n° " + id + " est introuvable" ) );
            throw new EmployeesNotFoundException( "L'employee n° " + id + " est introuvable" );
        }

        return employees;
    }

    @Override
    public Employees getEmployeeByUserName(String username)
    {
        return this.employeesRepository.findByFirstname(username);
    }

    //ERROR LOG A RAJOUTER ////////////////////////////////////////////////////////////////////////
    public LoginDao getLoginByEmployeeId(int id) throws CustomersNotFoundException
    {
        LoginDao userToModify = loginRepository.findByEmployeeNumber(id);

//        if (userToModify == null)
//        {
//            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "Le customer n° " + id +
//                    " est introuvable"));
//            throw new CustomersNotFoundException( "Le customer n° " + id + " est introuvable" );
//        }
//
//        else
//        {
//            return this.loginRepository.getUserByCustomerNumber(id);
//        }
        return userToModify;
    }

    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public LoginDao updatePassword(int id, UpdateEmployeePasswordForm updateEmployeePasswordForm)
    {
        // get member
        LoginDao user = this.getLoginByEmployeeId(id);

        // validate password and encrypt it
        try
        {
            user.setPassword( this.getBCryptPasswordEncoder().encode( updateEmployeePasswordForm.getPassword()));
        }

        catch (Exception e)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.INTERNAL_SERVER_ERROR,
                    "Password encoding failed"));
            e.getMessage();
            return null;
        }

        // update of password in database
        employeesRepository.updatePassword(id, user.getPassword());
        System.out.println( "Password correctly modified");

        return user;
    }

    @Override
    public Employees updateEmployeeProfile(int id, UpdateEmployeeForm updateEmployeeForm)
    {
        Employees employees = this.getEmployeeById(id);

        // SQL query needs strings -> null values management
        if (updateEmployeeForm.getUsername() != null)
            employees.setFirstname(updateEmployeeForm.getUsername());
        if (updateEmployeeForm.getPrivate_number() != null)
            employees.setPrivate_number(updateEmployeeForm.getPrivate_number());
        if (updateEmployeeForm.getEmail() != null)
            employees.setEmail(updateEmployeeForm.getEmail());

        this.employeesRepository.updateProfile( id);

        return employees;
    }

    @Override
    public LoginDao updateStatus(int id, UpdateEmployeeStatusForm updateEmployeeStatusForm)
    {
        LoginDao user = this.getLoginByEmployeeId(id);

        user.setStatus( updateEmployeeStatusForm.getStatus() );
        this.employeesRepository.updateStatus( id, user.getStatus().name()); // SQL query needs strings

        return user;
    }

    @Override
    public LoginDao getEmployeeByEmployeeNumber(int id) {
        return this.loginRepository.findByEmployeeNumber(id);
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteById(int id)
    {
        Employees employees = this.employeesRepository.findById(id);

        if ( employees == null )
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND,
                    String.format( "None employee could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None employee could be found with the id %d", id));
        }

        this.employeesRepository.deleteById(id);
    }


    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

    private void validationEmail(String email) throws EmployeeValidationException
    {

        if (email != null && employeesRepository.findByEmail( email ) != null)
        {
            errorLogServices
                    .recordLog( new Error_log( null, HttpStatus.BAD_REQUEST,
                            "Cette adresse mail n'est pas valide, merci d'en choisir une autre."));
            throw new EmployeeValidationException(
                    "Cette adresse mail n'est pas valide, merci d'en choisir une autre.");

        }
    }

    private void validationUsername(String username) throws EmployeeValidationException
    {
        if (username != null && employeesRepository.findByFirstname( username ) != null)
        {
            errorLogServices
                    .recordLog( new Error_log( null, HttpStatus.BAD_REQUEST,
                            "Ce pseudo n'est pas valide, merci d'en choisir un autre."));
            throw new EmployeeValidationException("Ce pseudo n'est pas valide, merci d'en choisir un autre.");
        }
    }

    private void validationPasswords(String password, String confirmation) throws EmployeeValidationException
    {
        if (!password.equals( confirmation))
        {
            errorLogServices
                    .recordLog(new Error_log( null, HttpStatus.BAD_REQUEST,
                            "Les deux mots de passe ne correspondent pas."));
            throw new EmployeeValidationException( "Les deux mots de passe ne correspondent pas.");
        }
    }
}
