package iventory.dataStorage;

import iventory.iventoryEntities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public static List<Customer> customerList = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

}
