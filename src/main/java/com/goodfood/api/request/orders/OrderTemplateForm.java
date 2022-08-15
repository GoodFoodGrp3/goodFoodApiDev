package com.goodfood.api.request.orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goodfood.api.entities.Order_details;

import java.sql.Timestamp;
import java.util.List;

public class OrderTemplateForm {

    private String orderId;
    private int customerId;
    private String status;
    private String comments;
    private List<OrderDetailsForm> orderDetails;

    public OrderTemplateForm(String orderId, int customerId, String status, String comments, List<OrderDetailsForm> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.comments = comments;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<OrderDetailsForm> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsForm> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
