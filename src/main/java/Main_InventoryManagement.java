package main.java;

import main.java.iventoryImplementation.InventoryService;
import main.java.util.ErrorHandling;
import main.java.util.Utils;
import static main.java.util.Colors.*;

public class Main_InventoryManagement {
    public static void main(String[] args) {
        Utils.readingData();
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
        InventoryService inventoryService = new InventoryService();
        switch (userChoice) {
            case 1:
                inventoryService.sellProduct("\n-TERMÉK ELADÁSA MENÜ-\n");
                break;
            case 2:
                inventoryService.restockProduct("\n-TERMÉK BEVÉTELEZÉSE MENÜ-\n");
                break;
            case 3:
                inventoryService.displayProductList("\n-ELÉRHETŐ TERMÉKEK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 4:
                inventoryService.displayCustomerList("\n-ÜGYFELEK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 5:
                inventoryService.displayTransactionList("\n-TRANZAKCIÓK MEGJELENÍTÉSE MENÜ-\n");
                break;
            case 6:
                inventoryService.saveDataToFile();
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
