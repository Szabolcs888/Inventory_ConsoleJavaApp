package inventory.dataIO;

import inventory.dataStorage.CustomerRepository;
import inventory.dataStorage.ProductRepository;
import inventory.dataStorage.SalesTransactionRepository;
import inventory.inventoryEntities.Customer;
import inventory.inventoryEntities.Product;
import inventory.inventoryEntities.SalesTransaction;
import inventory.util.FileUtils;

import java.util.List;

public class DataLoader {

    public static void loadAllData() {
        String productFile = FilePaths.PRODUCTS_FILE;
        String customerFile = FilePaths.CUSTOMERS_FILE;
        String transactionFile = FilePaths.TRANSACTIONS_FILE;

        loadProductsFromFile(productFile);
        loadCustomersFromFile(customerFile);
        loadTransactionsFromFile(transactionFile);
    }

    public static void loadProductsFromFile(String productsFile) {
        System.out.print("\nChecking the database of products : ");
        List<String> productListFromFile = FileUtils.readFromFile(productsFile);
        for (String line : productListFromFile) {
            String[] lineData = line.split(",");
            String productName = lineData[0];
            String productId = lineData[1];
            int unitPrice = Integer.parseInt(lineData[2]);
            int quantity = Integer.parseInt(lineData[3]);
            Product product = new Product(productName, productId, unitPrice, quantity);
            ProductRepository.addProduct(product);
        }
    }

    public static void loadCustomersFromFile(String customersFile) {
        System.out.print("Checking the database of customers: ");
        List<String> customerListFromFile = FileUtils.readFromFile(customersFile);
        for (String line : customerListFromFile) {
            String[] rowData = line.split(",");
            String customerName = rowData[0];
            int totalPurchases = Integer.parseInt(rowData[1]);
            String customerID = rowData[2];
            Customer customer = new Customer(customerName, customerID, totalPurchases);
            CustomerRepository.addCustomer(customer);
        }
    }

    public static void loadTransactionsFromFile(String transactionsFile) {
        System.out.print("Checking the database of transactions : ");
        List<String> transactionListFromFile = FileUtils.readFromFile(transactionsFile);
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
            SalesTransactionRepository.addSalesTransaction(salesTransaction);
        }
    }
}
