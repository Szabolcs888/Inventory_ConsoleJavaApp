package iventoryEntities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends ParentEntity {
    public String customerName;
    protected int totalPurchases;
    protected String customerID;
    public static List<Customer> customerList = new ArrayList<>();

    public Customer(String customerName, int totalPurchases, String customerID) {
        this.customerName = customerName;
        this.totalPurchases = totalPurchases;
        this.customerID = customerID;
    }

    public Customer() {
    }

    @Override
    String toFileString() {
        return null;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public String getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "iventoryEntities.Customer{" +
                "customerName='" + customerName + '\'' +
                ", totalPurchases=" + totalPurchases +
                ", customerID='" + customerID + '\'' +
                "} ";
    }
}
