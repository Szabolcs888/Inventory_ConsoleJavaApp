import iventoryImplementation.InventoryService;
import util.ErrorHandling;

public class Menu {

    static int menu(String text) {
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

    static void transactionSelector(int userChoice) {
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
            int userChoiceAgain = menu("\n-FŐMENÜ-\n");
            transactionSelector(userChoiceAgain);
        }
    }
}