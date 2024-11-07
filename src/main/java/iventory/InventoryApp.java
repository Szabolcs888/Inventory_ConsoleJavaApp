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
                    "\n1. Sell Product\n" +
                            "2. Receive Product\n" +
                            "3. Display Available Products\n" +
                            "4. Display Customers\n" +
                            "5. Display Transactions\n" +
                            "6. Save and Exit\n");
            if (userChoice < 1 || userChoice > 6)
                System.out.println("You can choose from 1 to 6!");
        } while (userChoice < 1 || userChoice > 6);
        return userChoice;
    }

    private static void transactionSelector(int userChoice) {
        switch (userChoice) {
            case 1:
                MenuOption1Sell menuOption1Sell = new MenuOption1Sell();
                menuOption1Sell.sellProduct("\n-SELL PRODUCT MENU-\n");
                break;
            case 2:
                MenuOption2GoodsReceipt menuOption2GoodsReceipt = new MenuOption2GoodsReceipt();
                menuOption2GoodsReceipt.goodsReceipt("\n-RECEIVE PRODUCT MENU-\n");
                break;
            case 3:
                MenuOption3DisplayProducts menuOption3DisplayProducts = new MenuOption3DisplayProducts();
                menuOption3DisplayProducts.displayProductList("\n-DISPLAY AVAILABLE PRODUCTS MENU-\n");
                break;
            case 4:
                MenuOption4DisplayCustomers menuOption4DisplayCustomers = new MenuOption4DisplayCustomers();
                menuOption4DisplayCustomers.displayCustomerList("\n-DISPLAY CUSTOMERS MENU-\n");
                break;
            case 5:
                MenuOption5DisplayTransactions menuOption5DisplayTransactions = new MenuOption5DisplayTransactions();
                menuOption5DisplayTransactions.displayTransactionList("\n-DISPLAY TRANSACTIONS MENU-\n");
                break;
            case 6:
                MenuOption6SaveData menuOption6SaveData = new MenuOption6SaveData();
                menuOption6SaveData.saveData();
                break;
        }
        if (userChoice != 6) {
            int userChoiceAgain = menuSelection("\n-MAIN MENU-\n");
            transactionSelector(userChoiceAgain);
        }
    }

    private static String getWelcomeMessage() {
        String welcomeMessage = "\n\nWelcome to the inventory system!" +
                GREEN_UNDERLINED.getColorCode() + "\n\nYou can choose from the following menu items:" + RESET.getColorCode();
        return welcomeMessage;
    }
}
