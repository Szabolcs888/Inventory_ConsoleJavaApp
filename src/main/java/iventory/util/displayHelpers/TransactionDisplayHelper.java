package iventory.util.displayHelpers;

import iventory.dataStorage.ProductRepository;
import iventory.iventoryEntities.SalesTransaction;

import java.util.List;

import static iventory.util.Colors.*;

public class TransactionDisplayHelper {

    public static void displayTransactionList(List<SalesTransaction> transactionList, String text) {
        System.out.println(text);
        if (transactionList.isEmpty()) {
            System.out.println("Jelenleg nincs tranzakció a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + transactionList.size() + " tranzakció szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (SalesTransaction item : transactionList) {
                System.out.println(item.getTransactionId() + ", Dátum: " + item.getTransactionDate() + ", Termék: " + item.getProductName() +
                        ", Mennyiség: " + item.getQuantitySold() + ", Fizetett összeg: " + item.getQuantitySold() + " * " + item.getUnitPrice() +
                        " = " + (item.getQuantitySold() * item.getUnitPrice()) + " HUF, Vásárló: " + item.getCustomerName() + " (" + item.getCustomerId() + ")");
            }
        }
        System.out.println();
    }

    public static void displayTransactionInfo(
            int productIndex, String productName, int quantitySold, String customerName, String customerId, boolean isRegisteredCustomer, String transactionDate) {
        System.out.println(GREEN.getColorCode() + "A TRANZAKCIÓ ADATAI: " + RESET.getColorCode() +
                "\nTermék neve: " + productName + " (" + ProductRepository.productList.get(productIndex).getProductId() + ")");
        if (isRegisteredCustomer)
            System.out.println("Ügyfél neve: : " + customerName + " (" + customerId + " / visszatérő ügyfél)");
        else
            System.out.println("Ügyfél neve: : " + customerName + " (" + customerId + " / újonnan regisztrált)");
        System.out.println("Eladott mennyiség: " + quantitySold +
                "\nFizetett összeg: " + quantitySold + " * " + ProductRepository.productList.get(productIndex).unitPrice +
                " = " + ProductRepository.productList.get(productIndex).unitPrice * quantitySold + " HUF" +
                "\nA tranzakció dátuma: " + transactionDate);
    }
}
