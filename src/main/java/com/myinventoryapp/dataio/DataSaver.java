package com.myinventoryapp.dataio;

import com.myinventoryapp.datastorage.CustomerRepository;
import com.myinventoryapp.inventoryentities.Product;
import com.myinventoryapp.util.FileUtils;
import com.myinventoryapp.datastorage.ProductRepository;
import com.myinventoryapp.datastorage.SalesTransactionRepository;
import com.myinventoryapp.inventoryentities.Customer;
import com.myinventoryapp.inventoryentities.SalesTransaction;

public class DataSaver {

    public void saveAllData() {
        String productFile = FilePaths.getProductsFilePath();
        String customerFile = FilePaths.getCustomersFilePath();
        String transactionFile = FilePaths.getTransactionsFilePath();

        saveProductsToFile(productFile);
        saveCustomersToFile(customerFile);
        saveTransactionsToFile(transactionFile);
    }

    void saveProductsToFile(String productFile) {
        String productListContent = "";
        for (Product item : ProductRepository.getProductList()) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductId() + "," + item.getUnitPrice() + "," + item.getQuantity() + "\n";
        }
        FileUtils.writeToFile(productListContent, productFile);
    }

    void saveCustomersToFile(String customerFile) {
        String customerListContent = "";
        for (Customer item : CustomerRepository.getCustomerList()) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(customerListContent, customerFile);
    }

    void saveTransactionsToFile(String transactionFile) {
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransactionRepository.getSalesTransactionList()) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProductName() +
                    "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomerName() + "," + item.getCustomerId() + "\n";
        }
        FileUtils.writeToFile(transactionListContent, transactionFile);
    }
}
