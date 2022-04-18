package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.EmployeeValidationException;
import com.goodfood.api.exceptions.customers.CustomersValidationException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service(value = "CustomersService")
public class CustomersServicesImpl implements CustomersService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    private ErrorLogServices errorLogServices;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    // ***************
    // CONSTRUCTOR
    // ***************

    public CustomersServicesImpl()
    {

    }


    // ***********************
    // POST/REGISTER
    // ***********************

    @Override
    public Customers registerCustomer(RegisterCustomerForm registerCustomerForm)
    {
        Customers customers = new Customers();

        Orders orders = new Orders();

        Comments comments = new Comments();

        // validation des attributs
        customers.setOrders(Collections.singleton(orders));
        customers.setComments(Collections.singleton(comments));

        validationPasswords(registerCustomerForm.getPassword(), registerCustomerForm.getCpassword());
        customers.setCustomername(registerCustomerForm.getUsername());
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

        try
        {
            // save in database
            customersRepository.save(customers);
        }

        catch (Exception e)
        {
            e.getMessage();
            return null;
        }

        return customers;
    }


    // ***************
    // GET
    // ***************

    @Override
    public List<Customers> getAllCustomers()
    {
        return (List<Customers>) this.customersRepository.findAll();
    }

    @Override
    public Customers getCustomerById(int id)
    {
        return this.customersRepository.findById(id);
    }

    @Override
    public Customers getCustomerByUserName(String username)
    {
        return this.customersRepository.findByCustomername(username);
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteById(int id)
    {
        customersRepository.deleteById(id);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Customers updateCustomerProfile(int id, UpdateCustomerForm updateCustomerForm)
    {
        Customers customers = this.getCustomerById(id);

        if (updateCustomerForm.getCustomername() != null)
            customers.setCustomername(updateCustomerForm.getCustomername());
        if (updateCustomerForm.getContact_lastname() != null)
            customers.setContact_lastname(updateCustomerForm.getContact_lastname());
        if (updateCustomerForm.getContact_firstname() != null)
            customers.setContact_firstname(updateCustomerForm.getContact_firstname());
        if (updateCustomerForm.getPhone() != null)
            customers.setPhone(updateCustomerForm.getPhone());
        if (updateCustomerForm.getAddressline1() != null)
            customers.setAddressline1(updateCustomerForm.getAddressline1());
        if (updateCustomerForm.getAddressline2() != null)
            customers.setAddressline2(updateCustomerForm.getAddressline2());
        if (updateCustomerForm.getCity() != null)
            customers.setCity(updateCustomerForm.getCity());
        if (updateCustomerForm.getState() != null)
            customers.setState(updateCustomerForm.getState());
        if (updateCustomerForm.getPostal_code() != null)
            customers.setPostal_code(updateCustomerForm.getPostal_code());
        if (updateCustomerForm.getCountry() != null)
            customers.setCountry(updateCustomerForm.getCountry());
        if (updateCustomerForm.getEmail() != null)
            customers.setEmail(updateCustomerForm.getEmail());

        customers.setModification_time(new Timestamp(System.currentTimeMillis()));

        customersRepository.updateCustomerProfile(id);

        return customers;
    }


    // ***********************
    // DATA VALIDATION METHODS
    // ***********************

    private void validationEmail(String email) throws CustomersValidationException
    {
        if (email != null && customersRepository.findByEmail( email ) != null)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Cette adresse mail n'est pas valide, merci d'en choisir une autre."));
            throw new EmployeeValidationException(
                    "Cette adresse mail n'est pas valide, merci d'en choisir une autre.");
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

    private void validationPasswords( String password, String confirmation ) throws CustomersValidationException
    {
        if (!password.equals( confirmation))
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Les deux mots de passe ne correspondent pas."));
            throw new EmployeeValidationException( "Les deux mots de passe ne correspondent pas.");
        }
    }
}
