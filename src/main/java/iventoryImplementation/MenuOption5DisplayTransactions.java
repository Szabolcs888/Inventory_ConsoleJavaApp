package main.java.iventoryImplementation;

import main.java.iventoryEntities.SalesTransaction;

import static main.java.util.Colors.GREEN;
import static main.java.util.Colors.RESET;

public class MenuOption5DisplayTransactions {

    // Megjeleníti az összes tranzakciót.
    public void displayTransactionList(String text) {
        System.out.println(text);
        if (SalesTransaction.transactionList.isEmpty()) {
            System.out.println("Jelenleg nincs tranzakció a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + SalesTransaction.transactionList.size() + " tranzakció szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (SalesTransaction item : SalesTransaction.transactionList) {
                System.out.println(item.getTransactionId() + ", Dátum: " + item.getTransactionDate() + ", Termék: " + item.getProduct() + ", Mennyiség: " + item.getQuantitySold() + ", Fizetett összeg: " + item.getQuantitySold() + " * " + item.getUnitPrice() + " = " + (item.getQuantitySold() * item.getUnitPrice()) + " HUF, Vásárló: " + item.getCustomer() + " (" + item.getCustId() + ")");
            }
        }
        System.out.println();
    }
}
