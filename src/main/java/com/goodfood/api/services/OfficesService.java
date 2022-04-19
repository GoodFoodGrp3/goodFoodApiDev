package com.goodfood.api.services;

import com.goodfood.api.entities.Offices;
import com.goodfood.api.exceptions.offices.OfficesNotFoundException;

import java.util.List;

public interface OfficesService
{
    List<Offices> getAllOffices() throws OfficesNotFoundException;
    Offices getOfficeById(int id) throws OfficesNotFoundException;

    Offices createOffices(int id, String city, String phone, String addressline1 , String addressline2, String state,
                          String country, String postal_code);

    Offices updateOffice(int id, String city, String phone, String addressLine1, String addressLine2, String state,
                         String country, String postal_code);
}
