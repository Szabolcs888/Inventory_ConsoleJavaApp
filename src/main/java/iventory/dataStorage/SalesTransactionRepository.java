package iventory.dataStorage;

import iventory.iventoryEntities.SalesTransaction;

import java.util.ArrayList;
import java.util.List;

public class SalesTransactionRepository {
    public static List<SalesTransaction> transactionList = new ArrayList<>();

    public static void addSalesTransaction(SalesTransaction salesTransaction) {
        transactionList.add(salesTransaction);
    }

    public static List<SalesTransaction> getSalesTransactionList() {
        return transactionList;
    }
}
