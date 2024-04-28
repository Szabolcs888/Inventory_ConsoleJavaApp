import iventoryImplementation.InventoryService;

import static util.Colors.*;

public class Main_InventoryManagement {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        inventoryService.loadProductsFromFile();
        inventoryService.loadTransactionsFromFile();
        inventoryService.loadCustomersFromFile();

        Menu menu = new Menu();
        menu.transactionSelector(menu.menuSelection("\n\nÜdvözöllek a készletnyilvántartó rendszerben!" +
                GREEN_UNDERLINED.getColorCode() + "\n\nAz alábbi menüpontok közül választhatsz:" + RESET.getColorCode()));
    }
}
