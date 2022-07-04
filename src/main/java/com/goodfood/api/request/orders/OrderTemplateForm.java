package com.goodfood.api.request.orders;

import com.goodfood.api.entities.Order_details;

import java.sql.Timestamp;
import java.util.List;

public class OrderTemplateForm {

    private String orderId;
    private int customerId;
    private Timestamp order_date;
    private Timestamp delivery_date;
    private Timestamp shipped_date;
    private String status;
    private String comments;
    private List<Order_details> orderDetails;

    public OrderTemplateForm(String orderId, int customerId, Timestamp order_date,
                             Timestamp delivery_date, Timestamp shipped_date, String status,
                             String comments, List<Order_details> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.shipped_date = shipped_date;
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

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Timestamp getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(Timestamp shipped_date) {
        this.shipped_date = shipped_date;
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

    public List<Order_details> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Order_details> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
