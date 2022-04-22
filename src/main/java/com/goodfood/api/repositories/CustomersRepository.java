package com.goodfood.api.repositories;

import com.goodfood.api.entities.Customers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomersRepository extends CrudRepository<Customers, Integer>
{
    Customers findById(int id);
    Customers findByEmail(String email);
    Customers findByFirstname(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE customers SET customername = :customername, " +
            "contact_lastname = :contact_lastname, contact_firstname = :contact_firstname, phone = :phone, " +
            "addressline1 = :addressline1, addressline2 = :addressline2, city = :city, state = :state, " +
            "postal_code = :postal_code, country = :country, email = :email WHERE id = :id")
    void updateCustomerProfile(@Param(value = "id") int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE customers SET password = :password WHERE id = :id")
    void updatePassword(@Param(value = "id") int id, @Param(value = "password") String password);
}
