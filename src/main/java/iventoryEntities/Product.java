package main.java.iventoryEntities;

import java.util.ArrayList;
import java.util.List;

import static main.java.util.Colors.*;

public class Product extends ParentEntity {
    public String productName;
    public String productCode;
    public int unitPrice;
    public int availableQuantity;
    public static List<Product> productList = new ArrayList<>();

    // A termék adatainak a kiíratása,
    //  új termék bevételezése után:
    //      - eladási tranzakció előtt
    //      - eladási tranzakció után
    //  ha már szerepel az adatbázisban:
    //      - bevételezés előtt
    //      - bevételezés után
    public static void displayProductInfo(String text, String productName, String productCode, int unitPrice, int availableQuantity) {
        System.out.println(GREEN.getColorCode() + "\n" + text + RESET.getColorCode());
        System.out.println("Termék neve: " + productName +
                ", Termékazonosító kód: " + productCode +
                ", Egységár: " + unitPrice + " HUF" +
                ", Elérhető mennyiség: " + availableQuantity);
    }

    // A termék adatainak a kiíratása, ha még nem szerepel az adatbázisban, bevételezés után
    public void displayProductInfoAfterRestock(String text) {
        System.out.println(GREEN.getColorCode() + text + RESET.getColorCode());
        System.out.println("Termék neve: " + productName +
                "\nTermékazonosító kód: " + productCode +
                "\nEgységár: " + unitPrice + " HUF" +
                "\nElérhető mennyiség: " + availableQuantity);
    }

    public Product(String productName, String productCode, int unitPrice, int availableQuantity) {
        this.productName = productName;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
    }

    public Product() {
    }

    @Override
    String toFileString() {
        return null;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "main.java.iventoryEntities.Product{" +
                "productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", availableQuantity=" + availableQuantity +
                "} ";
    }
}
