package main.java.iventoryImplementation;

import main.java.iventoryEntities.Product;

import static main.java.util.Colors.GREEN;
import static main.java.util.Colors.RESET;

public class MenuOption3DisplayProducts {

    // Megjeleníti az összes terméket a raktárban.
    public void displayProductList(String text) {
        System.out.println(text);
        Product product = new Product();
        if (product.productList.isEmpty()) {
            System.out.println("Jelenleg nincs termék a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + product.productList.size() + " tétel szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Product item : product.productList) {
                System.out.println("Név: " + item.getProductName() + ", Ár: " + item.getUnitPrice() + " HUF, Mennyiség: " + item.getAvailableQuantity() + ", Termékkód: " + item.getProductCode());
            }
        }
        System.out.println();
    }
}
