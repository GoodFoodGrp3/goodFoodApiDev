package com.goodfood.api.repositories;

import com.goodfood.api.entities.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity,Integer>
{
    Commodity findById(int id);
}
