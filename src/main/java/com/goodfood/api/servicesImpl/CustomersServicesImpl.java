package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.customers.CustomersNotFoundException;
import com.goodfood.api.exceptions.customers.CustomersValidationException;
import com.goodfood.api.repositories.CustomersRepository;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.request.customer.RegisterCustomerForm;
import com.goodfood.api.request.customer.UpdateCustomerForm;
import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xin.altitude.cms.common.util.SpringUtils;

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
    private CustomersService customersService;

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

        LoginDao loginEntity = new LoginDao();


        validationUsername(registerCustomerForm.getUsername());
        loginEntity.setLogin(registerCustomerForm.getUsername());

        customers.setLastname(registerCustomerForm.getLastname());

        customers.setFirstname(registerCustomerForm.getFirstname());

        customers.setPhone(registerCustomerForm.getPhone());

        customers.setAddressline1(registerCustomerForm.getAddressline1());

        customers.setAddressline2(registerCustomerForm.getAddressline2());

        customers.setCity(registerCustomerForm.getCity());

        customers.setState(registerCustomerForm.getState());

        customers.setPostal_code(registerCustomerForm.getPostalCode());

        customers.setCountry(registerCustomerForm.getCountry());

        validationEmail( registerCustomerForm.getEmail() );
        customers.setEmail(registerCustomerForm.getEmail());

        loginEntity.setStatus(Status.UTILISATEUR);

        validationPasswords(registerCustomerForm.getPassword(), registerCustomerForm.getCpassword());
        loginEntity.setPassword(getBCryptPasswordEncoder().encode(registerCustomerForm.getPassword()));

        try
        {
            // save in database
            customersRepository.save(customers);
            customersRepository.findByEmail(customers.getEmail());
            loginEntity.setCustomerNumber(customers);
            loginRepository.save(loginEntity);
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
    public List<Customers> getAllCustomers() throws CustomersNotFoundException
    {
        List<Customers> getAllCustomers = (List<Customers>) customersRepository.findAll();

        if (getAllCustomers == null || getAllCustomers.isEmpty())
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND,
                    "Aucun customers trouvé"));
            throw new CustomersNotFoundException( "Aucun customers trouvé" );
        }

        else
        {
            return (List<Customers>) this.customersRepository.findAll();
        }
    }

    @Override
    public Customers getCustomerById(int id) throws CustomersNotFoundException
    {
        Customers customers = customersRepository.findById(id);

        if (customers == null)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND, "Le customer n° " + id +
                    " est introuvable"));
            throw new CustomersNotFoundException( "Le customer n° " + id + " est introuvable" );
        }

        else
        {
            return this.customersRepository.findById(id);
        }
    }

    @Override
    public Customers getCustomerByEmail(String email)
    {
        return this.customersRepository.findByEmail(email);
    }


    // ***************
    // DELETE
    // ***************

    @Override
    public void deleteById(int id)
    {
        Customers customers = this.customersRepository.findById(id);

        if ( customers == null )
        {
            this.errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None customer could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None customer could be found with the id %d", id));
        }

        this.customersRepository.deleteById(id);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Customers updateCustomerProfile(int id, UpdateCustomerForm updateCustomerForm)
    {
        Customers customers = this.getCustomerById(id);

        if (updateCustomerForm.getContact_lastname() != null)
            customers.setLastname(updateCustomerForm.getContact_lastname());
        if (updateCustomerForm.getContact_firstname() != null)
            customers.setFirstname(updateCustomerForm.getContact_firstname());
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

//        customers.setModification_time(new Timestamp(System.currentTimeMillis()));

        customersRepository.updateCustomerProfile(id);

        return customers;
    }

    @Override
    public LoginDao updatePassword(int id, UpdateUserPasswordForm updateCustomerPasswordForm) {
        // get member
        LoginDao user = this.getLoginByCustomerId(id);

        // validate password and encrypt it
        try
        {
            user.setPassword( this.getBCryptPasswordEncoder().encode( updateCustomerPasswordForm.getPassword()));
        }

        catch (Exception e)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.INTERNAL_SERVER_ERROR,
                    "Password encoding failed"));
            e.getMessage();
            return null;
        }

        // update of password in database
        customersRepository.updatePassword(id, user.getPassword());
        System.out.println( "Password correctly modified");

        return user;
    }

    @Override
    public LoginDao getCustomerByCustomerId(int id) {
        return this.loginRepository.findByEmployeeNumber(id);
    }

    @Override
    public Customers getCurrentCustomer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return this.customersService.getCustomerByEmail((username));
    }

    @Override
    public String getStatus(String username)
    {
        LoginDao user = this.loginRepository.findByLogin(username);
        Status status = user.getStatus();
        return status.name();
    }

    public LoginDao getLoginByCustomerId(int id) throws CustomersNotFoundException
    {
        LoginDao userToModify = loginRepository.findByEmployeeNumber(id);

        if (userToModify == null)
        {
            errorLogServices.recordLog(new ErrorLog( null, HttpStatus.NOT_FOUND, "Le customer n° " + id +
                    " est introuvable"));
            throw new CustomersNotFoundException( "Le customer n° " + id + " est introuvable" );
        }

        else
        {
            return userToModify;
        }
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
            throw new CustomersValidationException(
                    "Cette adresse mail n'est pas valide, merci d'en choisir une autre.");
        }
    }

    private void validationUsername( String username ) throws CustomersValidationException
    {
        if ( username != null && customersRepository.findByFirstname( username ) != null )
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Ce pseudo n'est pas valide, merci d'en choisir un autre." ) );
            throw new CustomersValidationException( "Ce pseudo n'est pas valide, merci d'en choisir un autre." );
        }
    }

    private void validationPasswords( String password, String confirmation ) throws CustomersValidationException
    {
        if (!password.equals( confirmation))
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.BAD_REQUEST,
                            "Les deux mots de passe ne correspondent pas."));
            throw new CustomersValidationException( "Les deux mots de passe ne correspondent pas.");
        }
    }
}
