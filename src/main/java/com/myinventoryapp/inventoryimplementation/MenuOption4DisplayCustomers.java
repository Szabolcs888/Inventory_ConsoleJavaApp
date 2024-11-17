package com.myinventoryapp.inventoryimplementation;

import com.myinventoryapp.util.displayhelpers.CustomerDisplayHelper;
import com.myinventoryapp.datastorage.CustomerRepository;
import com.myinventoryapp.inventoryentities.Customer;

import java.util.List;

public class MenuOption4DisplayCustomers {

    public void displayCustomerList(String text) {
        List<Customer> customerList = CustomerRepository.getCustomerList();
        CustomerDisplayHelper.displayCustomerList(customerList, text);
    }
}
