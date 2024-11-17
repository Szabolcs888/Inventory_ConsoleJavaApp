package com.myinventoryapp.util.testutils;

import java.nio.file.Paths;

public class TestFilePaths {
    public static final String TEST_PRODUCTS_FILE = "src/test/resources/testInventoryData/testProductList.txt";
    public static final String TEST_CUSTOMERS_FILE = "src/test/resources/testInventoryData/testCustomerList.txt";
    public static final String TEST_TRANSACTIONS_FILE = "src/test/resources/testInventoryData/testTransactionList.txt";

    public static String getTestProductFilePath() {
        return Paths.get(TEST_PRODUCTS_FILE).toString();
    }

    public static String getTestCustomerFilePath() {
        return Paths.get(TEST_CUSTOMERS_FILE).toString();
    }

    public static String getTestTransactionFilePath() {
        return Paths.get(TEST_TRANSACTIONS_FILE).toString();
    }
}
