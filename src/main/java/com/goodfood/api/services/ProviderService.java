package com.goodfood.api.services;

import com.goodfood.api.entities.Provider;

import java.util.List;

public interface ProviderService
{
    List<Provider> getAllProviders();
    Provider getProviderById (int id);
    //void deleteProviderById( int id );

    Provider updateProvider( int id, String provider_name, String addressline, String email, String phone,
                             String country, String postal_code, String state );

    Provider createProviders(int id, String provider_name, String addressline, String email , String phone,
                             String country, String postal_code, String state );
}
