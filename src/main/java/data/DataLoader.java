package main.java.data;

import main.java.iventoryEntities.Customer;
import main.java.iventoryEntities.Product;
import main.java.iventoryEntities.SalesTransaction;
import main.java.util.Utils;

import java.util.List;

public class DataLoader {
    public static void loadAllData() {
        loadProductsFromFile();
        loadTransactionsFromFile();
        loadCustomersFromFile();
    }

    public static void loadProductsFromFile() {
        System.out.print("Checking the database of products : ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/inventoryList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String prod = rowData[0];
            String id = rowData[1];
            int price = Integer.parseInt(rowData[2]);
            int quantity = Integer.parseInt(rowData[3]);
            Product product = new Product(prod, id, price, quantity);
            Product.productList.add(product);
        }
    }

    public static void loadCustomersFromFile() {
        System.out.print("Checking the database of customers: ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/customerList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String customerName = rowData[0];
            int totalPurchase = Integer.parseInt(rowData[1]);
            String customerID = rowData[2];
            Customer customer = new Customer(customerName, totalPurchase, customerID);
            Customer.customerList.add(customer);
        }
    }

    public static void loadTransactionsFromFile() {
        System.out.print("Checking the database of transactions : ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/transactionList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String transactionId = rowData[0];
            String transactionDate = rowData[1];
            String product = rowData[2];
            int quantitySold = Integer.parseInt(rowData[3]);
            int unitPrice = Integer.parseInt(rowData[4]);
            String customer = rowData[5];
            String custId = rowData[6];
            SalesTransaction salesTransaction = new SalesTransaction(transactionId, product, customer, custId, quantitySold, unitPrice, transactionDate);
            SalesTransaction.transactionList.add(salesTransaction);
        }
    }


}
