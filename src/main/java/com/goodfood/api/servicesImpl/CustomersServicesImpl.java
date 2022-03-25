package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.EmployeeValidationException;
import com.goodfood.api.exceptions.customers.CustomersValidationException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service( value = "CustomersService" )
public class CustomersServicesImpl implements CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public CustomersServicesImpl() {
    }


    // ***********************
    // DATA PROCESSING
    // ***********************

    @Override
    public Customers registerCustomer(RegisterCustomerForm registerCustomerForm) {

        Customers customers = new Customers();

        Orders orders = new Orders();

        Comments comments = new Comments();

        Employees employees = new Employees();

        // validation des attributs

        customers.setOrders(Collections.singleton(orders));
        customers.setComments(Collections.singleton(comments));

        validationPasswords(registerCustomerForm.getPassword(), registerCustomerForm.getCpassword());
        customers.setCustomer_name(registerCustomerForm.getUsername());
        customers.setContact_lastname(registerCustomerForm.getLastname());
        customers.setContact_firstname(registerCustomerForm.getFirstname());
        customers.setPhone(registerCustomerForm.getPhone());
        customers.setAddressline1(registerCustomerForm.getAddressline1());
        customers.setAddressline2(registerCustomerForm.getAddressline2());
        customers.setCity(registerCustomerForm.getCity());
        customers.setState(registerCustomerForm.getState());
        customers.setPostal_code(registerCustomerForm.getPostalCode());
        customers.setCountry(registerCustomerForm.getCountry());
        validationEmail( registerCustomerForm.getEmail() );
        customers.setEmail(registerCustomerForm.getEmail());
        customers.setPassword(this.bCryptPasswordEncoder.encode(registerCustomerForm.getPassword()));

        try {
            // save in database
            customersRepository.save( customers );
        } catch ( Exception e ) {
            e.getMessage();
            return null;
        }

        return customers;
    }


    @Override
    public List<Customers> getAllCustomers() {
        return (List<Customers>) this.customersRepository.findAll();
    }

    @Override
    public Customers getCustomerById(int id) {
        return this.customersRepository.findById(id);
    }


    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

    private void validationEmail( String email ) throws CustomersValidationException {

        if ( email != null && customersRepository.findByEmail( email ) != null ) {

            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Cette adresse mail n'est pas valide, merci d'en choisir une autre." ) );
            throw new EmployeeValidationException(
                    "Cette adresse mail n'est pas valide, merci d'en choisir une autre." );

        }
    }

   /* private void validationUsername( String username ) throws CustomersValidationException {

        if ( username != null && customersRepository.findByFirstname( username ) != null ) {

            errorLogServices
                    .recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Ce pseudo n'est pas valide, merci d'en choisir un autre." ) );
            throw new EmployeeValidationException( "Ce pseudo n'est pas valide, merci d'en choisir un autre." );

        }

    }*/

    private void validationPasswords( String password, String confirmation ) throws CustomersValidationException {

        if ( !password.equals( confirmation ) ) {

            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Les deux mots de passe ne correspondent pas." ) );
            throw new EmployeeValidationException( "Les deux mots de passe ne correspondent pas." );

        }

    }
}
