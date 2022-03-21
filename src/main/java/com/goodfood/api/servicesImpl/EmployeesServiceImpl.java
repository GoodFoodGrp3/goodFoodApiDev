package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.entities.Order_commodity;
import com.goodfood.api.exceptions.MemberValidationException;
import com.goodfood.api.repositories.EmployeesRepository;
import com.goodfood.api.request.member.RegisterForm;
import com.goodfood.api.services.EmployeesService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service( value = "EmployeesService" )
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    // contructor
    public EmployeesServiceImpl() {
    }

    // ***********************
    // DATA PROCESSING
    // ***********************

    @Override
    public Employees registerMember(RegisterForm registerForm) {

        Employees employees = new Employees();

        Offices offices = new Offices();

        Order_commodity order_commodity = new Order_commodity();

        // validation des attributs
        validationEmail( registerForm.getEmail() );
        employees.setEmail(registerForm.getEmail());
        validationUsername(registerForm.getUsername());
        validationPasswords(registerForm.getPassword(), registerForm.getCpassword());
        employees.setFirstname(registerForm.getUsername());
        offices.setId(registerForm.getSuccursale());
        employees.setOffice_id(offices);
        employees.setOrder_commodity(Collections.singleton(order_commodity));
        employees.setStatus(registerForm.getStatus());
        employees.setPassword(this.bcrypt.encode(registerForm.getPassword()));

        try {
            // save in database
            employeesRepository.save( employees );
        } catch ( Exception e ) {
            e.getMessage();
            return null;
        }

        return employees;
    }

    @Override
    public Employees getEmployeesByFirstName(String username) {
        return this.employeesRepository.findByFirstname(username);
    }

    @Override
    public List<Employees> getAllEmployees() {
        return (List<Employees>) this.employeesRepository.findAll();
    }

    @Override
    public Employees getEmployeeById(int id) {
        return this.employeesRepository.findById(id);
    }



    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

    private void validationEmail( String email ) throws MemberValidationException {

        if ( email != null && employeesRepository.findByEmail( email ) != null ) {

            errorLogServices
                    .recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Cette adresse mail n'est pas valide, merci d'en choisir une autre." ) );
            throw new MemberValidationException(
                    "Cette adresse mail n'est pas valide, merci d'en choisir une autre." );

        }
    }

    private void validationUsername( String username ) throws MemberValidationException {

        if ( username != null && employeesRepository.findByFirstname( username ) != null ) {

            errorLogServices
                    .recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Ce pseudo n'est pas valide, merci d'en choisir un autre." ) );
            throw new MemberValidationException( "Ce pseudo n'est pas valide, merci d'en choisir un autre." );

        }

    }

    private void validationPasswords( String password, String confirmation ) throws MemberValidationException {

        if ( !password.equals( confirmation ) ) {

            errorLogServices
                    .recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Les deux mots de passe ne correspondent pas." ) );
            throw new MemberValidationException( "Les deux mots de passe ne correspondent pas." );

        }

    }
}
