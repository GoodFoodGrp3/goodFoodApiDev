package com.goodfood.api.repositories;

import com.goodfood.api.entities.Order_commodity_details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityOrderDetailsRepository extends CrudRepository<Order_commodity_details, Integer> {
    List<Order_commodity_details> findById (String orderNumber);
}
