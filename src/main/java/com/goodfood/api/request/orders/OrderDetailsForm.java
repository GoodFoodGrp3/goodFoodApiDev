package com.goodfood.api.request.orders;

public class OrderDetailsForm {

    private int product_id;

    private String product_name;

    private int code_tva_id;

    private int quantity_ordered;

    private double priceEach;

    private int order_line_number;

    public OrderDetailsForm(int product_id, String product_name, int code_tva_id,
                            int quantity_ordered, double priceEach, int order_line_number) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.code_tva_id = code_tva_id;
        this.quantity_ordered = quantity_ordered;
        this.priceEach = priceEach;
        this.order_line_number = order_line_number;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public int getOrder_line_number() {
        return order_line_number;
    }

    public void setOrder_line_number(int order_line_number) {
        this.order_line_number = order_line_number;
    }
}
