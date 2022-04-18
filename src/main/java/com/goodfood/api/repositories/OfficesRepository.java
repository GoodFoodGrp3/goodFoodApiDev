package com.goodfood.api.repositories;

import com.goodfood.api.entities.Offices;
import org.springframework.data.repository.CrudRepository;

public interface OfficesRepository extends CrudRepository<Offices,Integer>
{
    Offices findById (int id);
}
