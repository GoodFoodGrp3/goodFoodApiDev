package com.goodfood.api.services;

import com.goodfood.api.entities.Offices;

import java.util.List;

public interface OfficesService {

    List<Offices> getAllOffices();
    Offices getOfficeById( int id );
}
