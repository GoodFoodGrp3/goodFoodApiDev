package com.goodfood.api.repositories;

import com.goodfood.api.entities.Taxe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeRepository extends CrudRepository<Taxe,Integer> {
    Taxe findById (int id);
}
