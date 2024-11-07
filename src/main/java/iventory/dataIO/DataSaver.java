package iventory.dataIO;

import iventory.dataStorage.CustomerRepository;
import iventory.dataStorage.ProductRepository;
import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.Customer;
import iventory.iventoryEntities.Product;
import iventory.iventoryEntities.SalesTransaction;
import iventory.util.FileUtils;

public class DataSaver {

    public static void saveProductsToFile() {
        String productListContent = "";
        for (Product item : ProductRepository.productList) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductId() + "," + item.getUnitPrice() + "," + item.getQuantity() + "\n";
        }
        FileUtils.writeToFile(productListContent, "src/main/resources/inventoryData/productList.txt");
    }

    public static void saveCustomersToFile() {
        String customerListContent = "";
        for (Customer item : CustomerRepository.customerList) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(customerListContent, "src/main/resources/inventoryData/customerList.txt");
    }

    public static void saveTransactionsToFile() {
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransactionRepository.transactionList) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProductName() +
                    "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomerName() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(transactionListContent, "src/main/resources/inventoryData/transactionList.txt");
    }
}
