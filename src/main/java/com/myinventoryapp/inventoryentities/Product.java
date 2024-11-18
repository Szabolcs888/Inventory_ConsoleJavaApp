package com.myinventoryapp.inventoryentities;

public class Product {
    private String productName;
    private String productId;
    private int unitPrice;
    private int quantity;

    public Product(String productName, String productId, int unitPrice, int quantity) {
        this.productName = productName;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
