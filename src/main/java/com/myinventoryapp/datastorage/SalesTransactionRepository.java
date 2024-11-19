package com.myinventoryapp.datastorage;

import com.myinventoryapp.inventoryentities.SalesTransaction;

import java.util.ArrayList;
import java.util.List;

public class SalesTransactionRepository {
    private static List<SalesTransaction> transactionList = new ArrayList<>();

    public static void addSalesTransaction(SalesTransaction salesTransaction) {
        transactionList.add(salesTransaction);
    }

    public static void clearSalesTransactionList() {
        transactionList.clear();
    }

    public static List<SalesTransaction> getSalesTransactionList() {
        return transactionList;
    }
}
