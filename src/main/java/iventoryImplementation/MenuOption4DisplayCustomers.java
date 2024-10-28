package main.java.iventoryImplementation;

import main.java.iventoryEntities.Customer;

import static main.java.util.Colors.GREEN;
import static main.java.util.Colors.RESET;

public class MenuOption4DisplayCustomers {

    // Megjeleníti az összes ügyfelet.
    public void displayCustomerList(String text) {
        System.out.println(text);
        if (Customer.customerList.isEmpty()) {
            System.out.println("Jelenleg nincs regisztrált ügyfél a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + Customer.customerList.size() + " regisztrált ügyfél szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Customer item : Customer.customerList) {
                System.out.println("Név: " + item.getCustomerName() + " (" + item.getCustomerID() + "), Összes vásárlás: " + item.getTotalPurchases() + " HUF");
            }
        }
        System.out.println();
    }
}
