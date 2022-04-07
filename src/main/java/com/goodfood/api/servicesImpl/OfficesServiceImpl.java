package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Offices;
import com.goodfood.api.repositories.OfficesRepository;
import com.goodfood.api.services.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "OfficesService" )
public class OfficesServiceImpl implements OfficesService {

    @Autowired
    OfficesRepository officesRepository;

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
}
