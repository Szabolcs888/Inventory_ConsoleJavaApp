package com.myinventoryapp.util.testutils;

import com.myinventoryapp.datastorage.CustomerRepository;
import com.myinventoryapp.datastorage.ProductRepository;
import com.myinventoryapp.datastorage.SalesTransactionRepository;

public class TestUtils {

    public static void reset() {
        ProductRepository.clearProductData();
        CustomerRepository.clearCustomerData();
        SalesTransactionRepository.clearSalesTransactionData();
    }
}
