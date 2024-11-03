package iventory.iventoryImplementation;

import iventory.dataStorage.CustomerRepository;
import iventory.iventoryEntities.Customer;
import iventory.util.displayHelpers.CustomerDisplayHelper;

import java.util.List;

public class MenuOption4DisplayCustomers {

    public void displayCustomerList(String text) {
        List<Customer> customerList = CustomerRepository.getCustomerList();
        CustomerDisplayHelper.displayCustomerList(customerList, text);
    }
}
