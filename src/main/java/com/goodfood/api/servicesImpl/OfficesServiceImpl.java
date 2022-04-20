package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.offices.OfficesNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.OfficesRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service(value = "OfficesService")
public class OfficesServiceImpl implements OfficesService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    OfficesRepository officesRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Offices> getAllOffices() throws OfficesNotFoundException
    {
        List<Offices> getAllOffices = (List<Offices>) officesRepository.findAll();

        if (getAllOffices == null || getAllOffices.isEmpty())
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "Aucun office trouvé"));
            throw new ProductsNotFoundException( "Aucun office trouvé" );
        }

        return getAllOffices;
    }

    @Override
    public Offices getOfficeById(int id) throws OfficesNotFoundException
    {
        Offices offices = officesRepository.findById(id);

        if(offices == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "L'office n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "L'office n° " + id + " est introuvable");
        }

        else
        {
            return offices;
        }
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Offices createOffices(int id, String city, String phone, String addressline1, String addressline2,
                                 String state, String country, String postal_code)
    {
        final Offices offices = new Offices(city,phone,addressline1,addressline2,state,country,postal_code);
        return  this.officesRepository.save(offices);
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Offices updateOffice(int id, String city, String phone, String addressLine1, String addressLine2,
                                String state, String country, String postal_code)
    {
        Offices offices = this.officesRepository.findById(id);

        if ( offices == null )
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND,
                    String.format( "None offices could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format("None offices could be found with the id %d", id));
        }

        offices.setCity(city);
        offices.setPhone(phone);
        offices.setAddressLine1(addressLine1);
        offices.setAddressLine2(addressLine2);
        offices.setState(state);
        offices.setCountry(country);
        offices.setPostalCode(postal_code);
        officesRepository.save(offices);
        return offices;
    }
}
