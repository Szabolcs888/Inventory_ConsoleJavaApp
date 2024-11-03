package iventory.iventoryImplementation;

import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.SalesTransaction;
import iventory.util.displayHelpers.TransactionDisplayHelper;

import java.util.List;

public class MenuOption5DisplayTransactions {

    public void displayTransactionList(String text) {
        List<SalesTransaction> transactionList = SalesTransactionRepository.getSalesTransactionList();
        TransactionDisplayHelper.displayTransactionList(transactionList, text);
    }
}
