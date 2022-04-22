package com.goodfood.api.services;

import com.goodfood.api.entities.ErrorLog;

import java.util.List;

public interface ErrorLogServices
{
    public void recordLog(ErrorLog errorLog);
    public List<ErrorLog> getErrorLogs();
}
