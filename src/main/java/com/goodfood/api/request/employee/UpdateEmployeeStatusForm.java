package com.goodfood.api.request.employee;


import com.goodfood.api.entities.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UpdateEmployeeStatusForm
{
    @Enumerated( EnumType.STRING )
    private Status status;

    public UpdateEmployeeStatusForm()
    {

    }

    public UpdateEmployeeStatusForm(Status status )
    {
        super();
        this.status = status;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
