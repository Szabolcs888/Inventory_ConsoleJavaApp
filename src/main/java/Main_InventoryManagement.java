package main.java;

import main.java.data.DataLoader;
import main.java.iventoryImplementation.*;
import main.java.util.ErrorHandling;
import main.java.util.Utils;
import static main.java.util.Colors.*;

public class Main_InventoryManagement {
    public static void main(String[] args) {
        DataLoader.loadAllData();
        transactionSelector(menuSelection(getWelcomeMessage1() + GREEN_UNDERLINED.getColorCode() + getWelcomeMessage2() + RESET.getColorCode()));
    }

    private static int menuSelection(String text) {
        System.out.print(text);
        int userChoice;
        do {
            ErrorHandling errorHandling = new ErrorHandling();
            userChoice = errorHandling.validNumberPls(System.lineSeparator() +
                    "1. Termék eladása" + System.lineSeparator() +
                    "2. Termék bevételezése" + System.lineSeparator() +
                    "3. Elérhető termékek megjelenítése" + System.lineSeparator() +
                    "4. Ügyfelek megjelenítése" + System.lineSeparator() +
                    "5. Tranzakciók megjelenítése" + System.lineSeparator() +
                    "6. Save and Exit" + System.lineSeparator());
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
                MenuOption2Addition menuOption2Addition = new MenuOption2Addition();
                menuOption2Addition.restockProduct("\n-TERMÉK BEVÉTELEZÉSE MENÜ-\n");
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
                MenuOption5DisplayTransactions menuOption5DisplayTransactions =new MenuOption5DisplayTransactions();
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

    private static String getWelcomeMessage1() {
        String welcomeMessage1 = "\n\nÜdvözöllek a készletnyilvántartó rendszerben!";
        return welcomeMessage1;
    }

    private static String getWelcomeMessage2() {
        String welcomeMessage2 = "\n\nAz alábbi menüpontok közül választhatsz:";
        return welcomeMessage2;
    }
}
