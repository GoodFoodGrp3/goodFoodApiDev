package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Offices;
import com.goodfood.api.repositories.OfficesRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service( value = "OfficesService" )
public class OfficesServiceImpl implements OfficesService {

    @Autowired
    OfficesRepository officesRepository;

    @Autowired
    ErrorLogServices errorLogServices;

    @Override
    public List<Offices> getAllOffices() {
        return (List<Offices>) this.officesRepository.findAll();
    }

    @Override
    public Offices getOfficeById(int id) {
        return this.officesRepository.findById(id);
    }

    @Override
    public Offices createOffices(int id, String city, String phone, String addressline1, String addressline2, String state, String country, String postal_code) {
        final Offices offices = new Offices(city,phone,addressline1,addressline2,state,country,postal_code);
        return  this.officesRepository.save(offices);
    }

    @Override
    public Offices updateOffice(int id, String city, String phone, String addressLine1, String addressLine2, String state, String country, String postal_code) {
        Offices offices = this.officesRepository.findById( id );
        if ( offices == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) );
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
