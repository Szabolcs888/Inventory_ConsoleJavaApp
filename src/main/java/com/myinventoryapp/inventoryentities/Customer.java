package com.myinventoryapp.inventoryentities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return totalPurchases == customer.totalPurchases &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, customerId, totalPurchases);
    }
}
