package com.goodfood.api.repositories;

import com.goodfood.api.entities.LoginDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<LoginDao, Long> {
    LoginDao findByLogin(String login);
    LoginDao findByEmployeeNumber(int employeeNumber);
    LoginDao findByCustomerNumber(int customerNumber);
}
