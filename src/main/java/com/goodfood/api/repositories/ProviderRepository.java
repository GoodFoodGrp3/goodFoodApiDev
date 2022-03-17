package com.goodfood.api.repositories;

import com.goodfood.api.entities.Commodity;
import com.goodfood.api.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Integer> {
}
