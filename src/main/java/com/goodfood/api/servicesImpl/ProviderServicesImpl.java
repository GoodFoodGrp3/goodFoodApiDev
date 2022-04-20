package com.goodfood.api.servicesImpl;


import com.goodfood.api.entities.Error_log;
import com.goodfood.api.entities.Provider;
import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.exceptions.providers.ProviderNotFoundException;
import com.goodfood.api.repositories.ProviderRepository;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service(value = "ProviderService")
public class ProviderServicesImpl implements ProviderService
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ErrorLogServices errorLogServices;


    // ***************
    // GET
    // ***************

    @Override
    public List<Provider> getAllProviders() throws ProviderNotFoundException
    {
        List<Provider> getAllProviders = (List<Provider>) providerRepository.findAll();

        if (getAllProviders == null || getAllProviders.isEmpty())
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND, "Aucun fournisseur trouvé"));
            throw new ProductsNotFoundException( "Aucun fournisseur trouvé" );
        }

        return getAllProviders;
    }

    @Override
    public Provider getProviderById(int id) throws ProviderNotFoundException
    {
        Provider provider = providerRepository.findById(id);

        if(provider == null)
        {
            errorLogServices.recordLog(new Error_log( null, HttpStatus.NOT_FOUND, "Le fournisseur n° " + id
                    + " est introuvable"));
            throw new CommentsNotFoundException( "Le fournisseur n° " + id + " est introuvable");
        }

        else
        {
            return provider;
        }
    }


    // ***************
    // PUT/UPDATE
    // ***************

    @Override
    public Provider updateProvider(int id, String provider_name, String addressline, String email, String phone,
                                   String country, String postal_code, String state)
    {
        Provider provider = this.providerRepository.findById( id );

        if (provider == null)
        {
            errorLogServices.recordLog( new Error_log( null, HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id)));
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,
                    String.format( "None provider could be found with the id %d", id));
        }

        provider.setProvider_name(provider_name);
        provider.setAddressLine(addressline);
        provider.setEmail(email);
        provider.setPhone(phone);
        provider.setCountry(country);
        provider.setPostalCode(postal_code);
        provider.setState(state);
        providerRepository.save(provider);

        return provider;
    }


    // ***************
    // POST/CREATE
    // ***************

    @Override
    public Provider createProviders(int id, String provider_name, String addressline, String email, String phone,
                                    String country, String postal_code, String state)
    {
        final Provider providers = new Provider(provider_name,addressline,email,phone, country, postal_code,state);
        return this.providerRepository.save(providers);
    }
}
