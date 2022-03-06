package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.Provider;
import com.goodfood.api.repositories.ProviderRepository;
import com.goodfood.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( value = "ProviderService" )
public class ProviderServicesImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public List<Provider> getAllProviders() {
        return (List<Provider>) this.providerRepository.findAll();
    }
}
