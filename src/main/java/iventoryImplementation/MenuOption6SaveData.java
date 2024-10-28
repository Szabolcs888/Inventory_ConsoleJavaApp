package main.java.iventoryImplementation;

import main.java.data.DataSaver;

public class MenuOption6SaveData {

    public void saveData() {
        DataSaver.saveProductsToFile();
        DataSaver.saveTransactionsToFile();
        DataSaver.saveCustomersToFile();
        System.out.println("Az adatok elmentésre kerültek...");
    }
}
