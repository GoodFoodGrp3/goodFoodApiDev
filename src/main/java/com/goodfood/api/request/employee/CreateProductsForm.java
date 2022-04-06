package com.goodfood.api.request.employee;

import com.goodfood.api.entities.Categories;

public class CreateProductsForm {

    private int id;
    private Categories categories;
    private String productName;
    private String productDescription;
    private int quantityInStock;
    private int buyPrice;


    public CreateProductsForm(int id, Categories categories, String productName, String productDescription, int quantityInStock, int buyPrice) {
        this.id = id;
        this.categories = categories;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
}
