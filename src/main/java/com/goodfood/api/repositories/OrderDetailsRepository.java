package com.goodfood.api.repositories;

import com.goodfood.api.entities.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<Order_details, Integer> {
    List<Order_details> findById (String orderNumber);
}
