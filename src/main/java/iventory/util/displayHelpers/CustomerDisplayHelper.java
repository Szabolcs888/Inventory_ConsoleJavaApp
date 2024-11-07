package iventory.util.displayHelpers;

import iventory.dataStorage.CustomerRepository;
import iventory.iventoryEntities.Customer;

import java.util.List;

import static iventory.util.Colors.*;

public class CustomerDisplayHelper {

    public static void displayCustomerList(List<Customer> customerList, String text) {
        System.out.println(text);
        if (CustomerRepository.customerList.isEmpty()) {
            System.out.println("There are currently no registered customers in the inventory!");
        } else {
            System.out.println(GREEN.getColorCode() + "There are a total of " + CustomerRepository.customerList.size() +
                    " registered customers in the inventory:" + RESET.getColorCode());
            for (Customer item : customerList) {
                System.out.println("Name: " + item.getCustomerName() + " (" + item.getCustomerId() +
                        "), Total purchases: " + item.getTotalPurchases() + " HUF");
            }
        }
        System.out.println();
    }
}
