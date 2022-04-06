package com.goodfood.api.request.employee;

import com.goodfood.api.entities.Employees;
import com.goodfood.api.entities.Provider;

public class CreateCommoditiesForm {

    private int id;
    private Provider providerId;
    private Employees employeeId;
    private String commodityName;
    private String commodityDescription;
    private int quantityinStock;
    private double buyPrice;
    private String vendorProvider;
    private int quantity;

    public CreateCommoditiesForm(int id, Provider providerId, Employees employeeId, String commodityName, String commodityDescription, int quantityinStock, double buyPrice, String vendorProvider, int quantity) {
        this.id = id;
        this.providerId = providerId;
        this.employeeId = employeeId;
        this.commodityName = commodityName;
        this.commodityDescription = commodityDescription;
        this.quantityinStock = quantityinStock;
        this.buyPrice = buyPrice;
        this.vendorProvider = vendorProvider;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
    }

    public Employees getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employees employeeId) {
        this.employeeId = employeeId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public int getQuantityinStock() {
        return quantityinStock;
    }

    public void setQuantityinStock(int quantityinStock) {
        this.quantityinStock = quantityinStock;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getVendorProvider() {
        return vendorProvider;
    }

    public void setVendorProvider(String vendorProvider) {
        this.vendorProvider = vendorProvider;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
