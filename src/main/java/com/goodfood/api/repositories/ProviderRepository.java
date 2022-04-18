package com.goodfood.api.repositories;

import com.goodfood.api.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Integer>
{
    Provider findById (int id);
    // ("UPDATE provider SET provider_name = :provider_name WHERE provider_id = :provider_id")
}
