package inventory.dataStorage;

import inventory.inventoryEntities.SalesTransaction;

import java.util.ArrayList;
import java.util.List;

public class SalesTransactionRepository {
    private static List<SalesTransaction> transactionList = new ArrayList<>();

    public static void addSalesTransaction(SalesTransaction salesTransaction) {
        transactionList.add(salesTransaction);
    }

    public static void clearSalesTransactionData() {
        transactionList.clear();
    }

    public static List<SalesTransaction> getSalesTransactionList() {
        return transactionList;
    }
}
