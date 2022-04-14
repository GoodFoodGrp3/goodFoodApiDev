package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Categories;
import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.repositories.ProviderRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service( value = "ProviderService" )
public class ProviderServicesImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ErrorLogServices errorLogServices;

    @Override
    public List<Provider> getAllProviders() {
        return (List<Provider>) this.providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(int id) {
        return this.providerRepository.findById(id);
    }

    @Override
    public Provider updateProvider(int id, String provider_name, String addressline, String email, String phone, String country, String postal_code, String state) {
        Provider provider = this.providerRepository.findById( id );
        if ( provider == null ) {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) ) );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id ) );
        }
        provider.setProvider_name( provider_name );
        provider.setAddressLine(addressline);
        provider.setEmail(email);
        provider.setPhone(phone);
        provider.setCountry(country);
        provider.setPostalCode(postal_code);
        provider.setState(state);
        providerRepository.save( provider );

        return provider;
    }

    @Override
    public Provider createProviders(int id, String provider_name, String addressline, String email, String phone, String country, String postal_code, String state) {
        final Provider providers = new Provider(provider_name,addressline,email,phone, country, postal_code,state);
        return this.providerRepository.save(providers);
    }




}
