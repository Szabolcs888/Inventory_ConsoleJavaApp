package com.myinventoryapp.inventoryimplementation;

import com.myinventoryapp.datastorage.SalesTransactionRepository;
import com.myinventoryapp.inventoryentities.SalesTransaction;
import com.myinventoryapp.util.displayhelpers.TransactionDisplayHelper;

import java.util.List;

public class MenuOption5DisplayTransactions {

    public void displayTransactionList(String text) {
        List<SalesTransaction> transactionList = SalesTransactionRepository.getSalesTransactionList();
        TransactionDisplayHelper.displayTransactionList(transactionList, text);
    }
}
