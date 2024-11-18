package com.myinventoryapp.inventoryentities;

public class Customer {
    private String customerName;
    private String customerId;
    private int totalPurchases;

    public Customer(String customerName, String customerId, int totalPurchases) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.totalPurchases = totalPurchases;
    }

    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }
}
