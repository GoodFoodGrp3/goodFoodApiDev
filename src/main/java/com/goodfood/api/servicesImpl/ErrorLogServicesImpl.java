package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.repositories.ErrorLogRepository;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "ErrorLogService")
public class ErrorLogServicesImpl implements ErrorLogServices
{
    // ***************
    // VARIABLE DE CLASS
    // ***************

    @Autowired
    private ErrorLogRepository errorLogRepository;


    // ***************
    // CONSTRUCTOR
    // ***************

    public ErrorLogServicesImpl()
    {

    }

    @Override
    public void recordLog( ErrorLog errorLog )
    {
        errorLogRepository.save( errorLog );
    }


    // ***************
    // GET
    // ***************

    @Override
    public List<ErrorLog> getErrorLogs()
    {
        return errorLogRepository.findAllOrderByIdDesc();
    }
}
