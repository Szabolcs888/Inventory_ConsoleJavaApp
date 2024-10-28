package main.java.data;

import main.java.iventoryEntities.Customer;
import main.java.iventoryEntities.Product;
import main.java.iventoryEntities.SalesTransaction;
import main.java.util.Utils;

public class DataSaver {

    // Adatok fájlba írása.
    public static void saveProductsToFile() {
        // Az árukészlet fájlba írása
        String productListContent = "";
        for (Product item : Product.productList) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductCode() + "," + item.getUnitPrice() + "," + item.getAvailableQuantity() + System.lineSeparator();
        }
        Utils.writeToFile(productListContent, "resources/inventoryList.txt");
    }

    // A tranzakciók fájlba írása
    public static void saveTransactionsToFile() {
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransaction.transactionList) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProduct() + "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomer() + "," + item.getCustId() + System.lineSeparator();
        }
        Utils.writeToFile(transactionListContent, "resources/transactionList.txt");
    }

    public static void saveCustomersToFile() {
        // Az ügyfelek fájlba írása
        String customerListContent = "";
        for (Customer item : Customer.customerList) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerID() + System.lineSeparator();
        }
        Utils.writeToFile(customerListContent, "resources/customerList.txt");
    }
}
