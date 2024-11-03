package iventory.util.displayHelpers;

import iventory.dataStorage.CustomerRepository;
import iventory.iventoryEntities.Customer;

import java.util.List;

import static iventory.util.Colors.*;

public class CustomerDisplayHelper {

    public static void displayCustomerList(List<Customer> customerList, String text) {
        System.out.println(text);
        if (CustomerRepository.customerList.isEmpty()) {
            System.out.println("Jelenleg nincs regisztrált ügyfél a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + CustomerRepository.customerList.size() +
                    " regisztrált ügyfél szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Customer item : customerList) {
                System.out.println("Név: " + item.getCustomerName() + " (" + item.getCustomerId() +
                        "), Összes vásárlás: " + item.getTotalPurchases() + " HUF");
            }
        }
        System.out.println();
    }
}
