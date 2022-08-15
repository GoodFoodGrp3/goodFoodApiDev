package com.goodfood.api.request.commodity;

import java.util.List;

public class OrderCommodityForm {

    private String orderCommodityId;
    private int employeeId;
    private String status;
    private List<OrderCommodityDetailsForm> orderCommodityDetailsForms;

    public OrderCommodityForm(String orderCommodityId, int employeeId, String status, List<OrderCommodityDetailsForm> orderCommodityDetailsForms) {
        this.orderCommodityId = orderCommodityId;
        this.employeeId = employeeId;
        this.status = status;
        this.orderCommodityDetailsForms = orderCommodityDetailsForms;
    }

    public String getOrderCommodityId() {
        return orderCommodityId;
    }

    public void setOrderCommodityId(String orderCommodityId) {
        this.orderCommodityId = orderCommodityId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderCommodityDetailsForm> getOrderCommodityDetailsForms() {
        return orderCommodityDetailsForms;
    }

    public void setOrderCommodityDetailsForms(List<OrderCommodityDetailsForm> orderCommodityDetailsForms) {
        this.orderCommodityDetailsForms = orderCommodityDetailsForms;
    }
}
