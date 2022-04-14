package com.goodfood.api.services;

import com.goodfood.api.entities.Offices;

import java.util.List;

public interface OfficesService {

    List<Offices> getAllOffices();
    Offices getOfficeById( int id );
    Offices createOffices(int id, String city, String phone, String addressline1 , String addressline2, String state, String country, String postal_code );

    Offices updateOffice(int id, String city, String phone, String addressLine1, String addressLine2, String state, String country, String postal_code);
}
