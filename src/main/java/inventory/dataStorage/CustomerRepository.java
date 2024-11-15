package inventory.dataStorage;

import inventory.inventoryEntities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static List<Customer> customerList = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public static void clearCustomerData() {
        customerList.clear();
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }
}
