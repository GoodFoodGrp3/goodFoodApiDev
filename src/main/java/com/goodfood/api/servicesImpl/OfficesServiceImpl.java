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
}
