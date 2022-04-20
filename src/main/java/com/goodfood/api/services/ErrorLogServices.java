package com.goodfood.api.services;

import com.goodfood.api.entities.Error_log;

import java.util.List;

public interface ErrorLogServices
{
    public void recordLog(Error_log errorLog);
    public List<Error_log> getErrorLogs();
}
