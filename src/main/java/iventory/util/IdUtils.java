package iventory.util;

import iventory.dataStorage.CustomerRepository;
import iventory.dataStorage.ProductRepository;
import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.Customer;
import iventory.iventoryEntities.Product;
import iventory.iventoryEntities.SalesTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdUtils {
    private static final Random random = new Random();

    public static int generateId() {
        int id;
        do {
            id = random.nextInt(1000000, 9999999) + 1;
        } while (isIdExisting(id));
        return id;
    }

    private static boolean isIdExisting(int id) {
        List<Integer> alreadyExistingIds = readIdsFromLists();
        return alreadyExistingIds.contains(id);
    }

    private static List<Integer> readIdsFromLists() {
        List<Integer> alreadyExistingIds = new ArrayList<>();

        List<Product> productList = ProductRepository.getProductList();
        for (Product product : productList) {
            alreadyExistingIds.add(Integer.parseInt(product.getProductId().split("r")[1]));
        }

        List<Customer> customerList = CustomerRepository.getCustomerList();
        for (Customer customer : customerList) {
            alreadyExistingIds.add(Integer.parseInt(customer.getCustomerId().split("D")[1]));
        }

        List<SalesTransaction> transactionList = SalesTransactionRepository.getSalesTransactionList();
        for (SalesTransaction transaction : transactionList) {
            alreadyExistingIds.add(Integer.parseInt(transaction.getTransactionId().split("d")[1]));
        }
        return alreadyExistingIds;
    }
}
