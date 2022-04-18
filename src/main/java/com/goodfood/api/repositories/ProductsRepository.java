package com.goodfood.api.repositories;

import com.goodfood.api.entities.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer>
{
    Products findById (int id);
}
