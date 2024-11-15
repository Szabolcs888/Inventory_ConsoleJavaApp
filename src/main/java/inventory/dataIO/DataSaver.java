package inventory.dataIO;

import inventory.dataStorage.CustomerRepository;
import inventory.dataStorage.ProductRepository;
import inventory.dataStorage.SalesTransactionRepository;
import inventory.inventoryEntities.Customer;
import inventory.inventoryEntities.Product;
import inventory.inventoryEntities.SalesTransaction;
import inventory.util.FileUtils;

public class DataSaver {

    public static void saveAllData() {
        String productFile = FilePaths.PRODUCTS_FILE;
        String customerFile = FilePaths.CUSTOMERS_FILE;
        String transactionFile = FilePaths.TRANSACTIONS_FILE;

        saveProductsToFile(productFile);
        saveCustomersToFile(customerFile);
        saveTransactionsToFile(transactionFile);
    }

    public static void saveProductsToFile(String productFile) {
        String productListContent = "";
        for (Product item : ProductRepository.getProductList()) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductId() + "," + item.getUnitPrice() + "," + item.getQuantity() + "\n";
        }
        FileUtils.writeToFile(productListContent, productFile);
    }

    public static void saveCustomersToFile(String customerFile) {
        String customerListContent = "";
        for (Customer item : CustomerRepository.getCustomerList()) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(customerListContent, customerFile);
    }

    public static void saveTransactionsToFile(String transactionFile) {
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransactionRepository.getSalesTransactionList()) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProductName() +
                    "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomerName() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(transactionListContent, transactionFile);
    }
}
