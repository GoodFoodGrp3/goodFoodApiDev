package com.goodfood.api.repositories;

import com.goodfood.api.entities.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>
{
    @Query( nativeQuery = true, value = "SELECT * FROM error_log ORDER BY date DESC LIMIT 100")
    List<ErrorLog> findAllOrderByIdDesc();
}
