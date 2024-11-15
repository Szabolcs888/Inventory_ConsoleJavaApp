package inventory.inventoryImplementation;

import inventory.dataStorage.SalesTransactionRepository;
import inventory.inventoryEntities.SalesTransaction;
import inventory.util.displayHelpers.TransactionDisplayHelper;

import java.util.List;

public class MenuOption5DisplayTransactions {

    public void displayTransactionList(String text) {
        List<SalesTransaction> transactionList = SalesTransactionRepository.getSalesTransactionList();
        TransactionDisplayHelper.displayTransactionList(transactionList, text);
    }
}
