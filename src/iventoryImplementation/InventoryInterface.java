package iventoryImplementation;

public interface InventoryInterface {

    void sellProduct(String text); // A termék eladásának folyamatát kezelő metódus.

    void restockProduct(String text); // A készletfeltöltés folyamatának metódusa

    void displayProductList(String text); // Megjeleníti az összes terméket a raktárban.

    void displayCustomerList(String text); // Megjeleníti az összes ügyfelet.

    void displayTransactionList(String text); // Megjeleníti az összes tranzakciót.

    void saveDataToFile(); // Adatok fájlba írása.

    void loadProductsFromFile(); //Termékek fájlból olvasása.

    void loadCustomersFromFile(); //Ügyféladatok fájlból olvasása.

    void loadTransactionsFromFile(); // Tranzakcióadatok fájlból olvasása
}
