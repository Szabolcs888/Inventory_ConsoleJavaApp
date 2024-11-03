package iventory;

import iventory.dataIO.DataLoader;
import iventory.iventoryImplementation.*;
import iventory.util.ErrorHandler;

import static iventory.util.Colors.*;

public class InventoryApp {

    public static void main(String[] args) {
        DataLoader.loadAllData();
        int userChoice = menuSelection(getWelcomeMessage());
        transactionSelector(userChoice);
    }

    private static int menuSelection(String text) {
        System.out.print(text);
        int userChoice;
        do {
            userChoice = ErrorHandler.getValidNumber(
                    "\n1. Termék eladása\n" +
                            "2. Termék bevételezése\n" +
                            "3. Elérhető termékek megjelenítése\n" +
                            "4. Ügyfelek megjelenítése\n" +
                            "5. Tranzakciók megjelenítése\n" +
                            "6. Mentés és Kilépés\n");
            if (userChoice < 1 || userChoice > 6)
                System.out.println("1-től 6-ig tudsz választani!");
        } while (userChoice < 1 || userChoice > 6);
        return userChoice;
    }

    private static void transactionSelector(int userChoice) {
        switch (userChoice) {
            case 1:
                MenuOption1Sell menuOption1Sell = new MenuOption1Sell();
                menuOption1Sell.sellProduct("\n-TERMÉK ELADÁSA MENÜ-\n");
                break;
            case 2:
                MenuOption2GoodsReceipt menuOption2GoodsReceipt = new MenuOption2GoodsReceipt();
                menuOption2GoodsReceipt.goodsReceipt("\n-TERMÉK BEVÉTELEZÉSE MENÜ-\n");
                break;
            case 3:
                MenuOption3DisplayProducts menuOption3DisplayProducts = new MenuOption3DisplayProducts();
                menuOption3DisplayProducts.displayProductList("\n-ELÉRHETŐ TERMÉKEK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 4:
                MenuOption4DisplayCustomers menuOption4DisplayCustomers = new MenuOption4DisplayCustomers();
                menuOption4DisplayCustomers.displayCustomerList("\n-ÜGYFELEK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 5:
                MenuOption5DisplayTransactions menuOption5DisplayTransactions = new MenuOption5DisplayTransactions();
                menuOption5DisplayTransactions.displayTransactionList("\n-TRANZAKCIÓK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 6:
                MenuOption6SaveData menuOption6SaveData = new MenuOption6SaveData();
                menuOption6SaveData.saveData();
                break;
        }
        if (userChoice != 6) {
            int userChoiceAgain = menuSelection("\n-FŐMENÜ-\n");
            transactionSelector(userChoiceAgain);
        }
    }

    private static String getWelcomeMessage() {
        String welcomeMessage = "\n\nÜdvözöllek a készletnyilvántartó rendszerben!" +
                GREEN_UNDERLINED.getColorCode() + "\n\nAz alábbi menüpontok közül választhatsz:" + RESET.getColorCode();
        return welcomeMessage;
    }
}
