package iventory.dataIO;

import iventory.dataStorage.CustomerRepository;
import iventory.dataStorage.ProductRepository;
import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.Customer;
import iventory.iventoryEntities.Product;
import iventory.iventoryEntities.SalesTransaction;
import iventory.util.FileUtils;

import java.util.List;

public class DataLoader {

    public static void loadAllData() {
        loadProductsFromFile();
        loadCustomersFromFile();
        loadTransactionsFromFile();
    }

    public static void loadProductsFromFile() {
        System.out.print("\nChecking the database of products : ");
        List<String> productListFromFile = FileUtils.readFromFile("src/main/resources/inventoryData/productList.txt");
        for (String line : productListFromFile) {
            String[] lineData = line.split(",");
            String productName = lineData[0];
            String productId = lineData[1];
            int unitPrice = Integer.parseInt(lineData[2]);
            int quantity = Integer.parseInt(lineData[3]);
            Product product = new Product(productName, productId, unitPrice, quantity);
            ProductRepository.productList.add(product);
        }
    }

    public static void loadCustomersFromFile() {
        System.out.print("Checking the database of customers: ");
        List<String> customerListFromFile = FileUtils.readFromFile("src/main/resources/inventoryData/customerList.txt");
        for (String line : customerListFromFile) {
            String[] rowData = line.split(",");
            String customerName = rowData[0];
            int totalPurchases = Integer.parseInt(rowData[1]);
            String customerID = rowData[2];
            Customer customer = new Customer(customerName, customerID, totalPurchases);
            CustomerRepository.customerList.add(customer);
        }
    }

    public static void loadTransactionsFromFile() {
        System.out.print("Checking the database of transactions : ");
        List<String> transactionListFromFile = FileUtils.readFromFile("src/main/resources/inventoryData/transactionList.txt");
        for (String line : transactionListFromFile) {
            String[] rowData = line.split(",");
            String transactionId = rowData[0];
            String transactionDate = rowData[1];
            String productName = rowData[2];
            int quantitySold = Integer.parseInt(rowData[3]);
            int unitPrice = Integer.parseInt(rowData[4]);
            String customerName = rowData[5];
            String customerID = rowData[6];
            SalesTransaction salesTransaction = new SalesTransaction(
                    transactionId, customerName, customerID, productName, quantitySold, unitPrice, transactionDate);
            SalesTransactionRepository.transactionList.add(salesTransaction);
        }
    }
}
