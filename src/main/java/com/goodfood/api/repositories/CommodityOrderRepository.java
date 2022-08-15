package com.goodfood.api.repositories;

import com.goodfood.api.entities.Order_commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityOrderRepository extends CrudRepository<Order_commodity, String> {
}
