package com.goodfood.api.repositories;

import com.goodfood.api.entities.Error_log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorLogRepository extends CrudRepository<Error_log, Long>
{
    @Query( nativeQuery = true, value = "SELECT * FROM error_log ORDER BY date DESC LIMIT 100")
    List<Error_log> findAllOrderByIdDesc();
}
