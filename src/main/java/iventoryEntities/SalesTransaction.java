package main.java.iventoryEntities;

import java.util.ArrayList;
import java.util.List;

import static main.java.util.Colors.*;

public class SalesTransaction extends ParentEntity {
    protected String transactionId = "trId" + generateId();
    protected String product;
    protected String customer;
    protected int quantitySold;
    protected int unitPrice;
    protected String custId;
    protected String transactionDate;
    public static List<SalesTransaction> transactionList = new ArrayList<>();

    public SalesTransaction(String transactionId, String product, String customer, String custId, int quantitySold, int unitPrice, String transactionDate) {
        this.transactionId = transactionId;
        this.product = product;
        this.customer = customer;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.custId = custId;
        this.transactionDate = transactionDate;
    }

    public SalesTransaction() {
    }

    public void displayTransactionInfo(int productIndex, boolean isRegisteredCustomer) {
        System.out.println(GREEN.getColorCode() + "A TRANZAKCIÓ ADATAI: " + RESET.getColorCode() +
                "\nTermék neve: " + product + " (" + Product.productList.get(productIndex).getProductCode() + ")");
        if (isRegisteredCustomer)
            System.out.println("Ügyfél neve: : " + customer + " (" + custId + " / visszatérő ügyfél)");
        else
            System.out.println("Ügyfél neve: : " + customer + " (" + custId + " / újonnan regisztrált)");
        System.out.println("Eladott mennyiség: " + quantitySold +
                "\nFizetett összeg: " + quantitySold + " * " + Product.productList.get(productIndex).unitPrice + " = " + Product.productList.get(productIndex).unitPrice * quantitySold + " HUF" +
                "\nA tranzakció dátuma: " + transactionDate);
    }

    @Override
    String toFileString() {
        return null;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "main.java.iventoryEntities.SalesTransaction{" +
                "product='" + product + '\'' +
                ", customer='" + customer + '\'' +
                ", quantitySold=" + quantitySold +
                ", transactionDate='" + transactionDate + '\'' +
                "} ";
    }
}
