package com.goodfood.api.request.commodity;

public class OrderCommodityDetailsForm {

    private int product_id;

    private String commodity_name;

    private String unit;

    private int code_tva_id;

    private int quantity_ordered;

    private double priceEach;

    public OrderCommodityDetailsForm(int product_id, String commodity_name, String unit,
                                     int code_tva_id, int quantity_ordered, double priceEach) {
        this.product_id = product_id;
        this.commodity_name = commodity_name;
        this.unit = unit;
        this.code_tva_id = code_tva_id;
        this.quantity_ordered = quantity_ordered;
        this.priceEach = priceEach;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCode_tva_id() {
        return code_tva_id;
    }

    public void setCode_tva_id(int code_tva_id) {
        this.code_tva_id = code_tva_id;
    }

    public int getQuantity_ordered() {
        return quantity_ordered;
    }

    public void setQuantity_ordered(int quantity_ordered) {
        this.quantity_ordered = quantity_ordered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }
}
