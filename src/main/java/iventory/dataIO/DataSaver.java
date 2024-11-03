package iventory.dataIO;

import iventory.dataStorage.CustomerRepository;
import iventory.dataStorage.ProductRepository;
import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.Customer;
import iventory.iventoryEntities.Product;
import iventory.iventoryEntities.SalesTransaction;
import iventory.util.Utils;

public class DataSaver {

    public static void saveProductsToFile() {
        String productListContent = "";
        for (Product item : ProductRepository.productList) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductId() + "," + item.getUnitPrice() + "," + item.getQuantity() + "\n";
        }
        Utils.writeToFile(productListContent, "src/main/resources/productList.txt");
    }

    public static void saveCustomersToFile() {
        String customerListContent = "";
        for (Customer item : CustomerRepository.customerList) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerId() + "\n";
        }
        Utils.writeToFile(customerListContent, "src/main/resources/customerList.txt");
    }

    public static void saveTransactionsToFile() {
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransactionRepository.transactionList) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProductName() +
                    "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomerName() + "," + item.getCustomerId() + "\n";
        }
        Utils.writeToFile(transactionListContent, "src/main/resources/transactionList.txt");
    }
}