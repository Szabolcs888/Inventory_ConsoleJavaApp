package inventory.inventoryImplementation;

import inventory.dataStorage.CustomerRepository;
import inventory.inventoryEntities.Customer;
import inventory.util.displayHelpers.CustomerDisplayHelper;

import java.util.List;

public class MenuOption4DisplayCustomers {

    public void displayCustomerList(String text) {
        List<Customer> customerList = CustomerRepository.getCustomerList();
        CustomerDisplayHelper.displayCustomerList(customerList, text);
    }
}
