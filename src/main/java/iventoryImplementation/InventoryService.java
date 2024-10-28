package main.java.iventoryImplementation;

import main.java.iventoryEntities.Customer;
import main.java.iventoryEntities.ParentEntity;
import main.java.iventoryEntities.Product;
import main.java.iventoryEntities.SalesTransaction;
import main.java.util.ErrorHandling;
import main.java.util.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static main.java.util.Colors.*;

public class InventoryService {
    private int productIndex = 0;
    private int customerIndex = 0;

    //-----1.MENÜPONT-----//
    // A termék eladásának folyamatát kezelő metódus
    public void sellProduct(String text) {
        System.out.println(text);
        String isAnotherSearch;
        Product product = new Product();
        do {
            if (!product.productList.isEmpty()) {
                String custName = getCustomerName();
                String prodName = getProductName();
                int quantity = getQuantity(prodName);
                boolean isRegisteredCustomer = customerRegistration(custName);
                transactionRegistration(isRegisteredCustomer, custName, prodName, quantity);
                product.productList.get(productIndex).setAvailableQuantity(product.productList.get(productIndex).getAvailableQuantity() - quantity);
                product.displayProductInfo("A TERMÉK ADATAI A TRANZAKCIÓ UTÁN:", product.productList.get(productIndex).productName, product.productList.get(productIndex).productCode, product.productList.get(productIndex).unitPrice, product.productList.get(productIndex).availableQuantity);
                ErrorHandling errorHandling = new ErrorHandling();
                isAnotherSearch = errorHandling.answerYoN("Szeretnél újabb termékeladást bejegyezni? (I/N)");
                System.out.println();
            } else {
                System.out.println("Jelenleg nincs eladható termék!\n");
                isAnotherSearch = "N";
            }
        } while (Objects.requireNonNull(isAnotherSearch).equalsIgnoreCase("I"));
    }

    private String getCustomerName() {
        String custName;
        do {
            Utils utils = new Utils();
            custName = utils.readFromUser("Kérem a vásárló nevét:");
            ErrorHandling errorHandling = new ErrorHandling();
            errorHandling.validName(custName);
        } while (custName.length() < 3 || custName.contains(","));
        return custName;
    }

    private String getProductName() {
        String prodName;
        int isProdInList = 0;
        int localAvailableQuantity;
        do {
            localAvailableQuantity = 1;
            Utils utils = new Utils();
            prodName = utils.readFromUser("\nKérem az eladandó termék nevét:");
            int i = 0;
            Product product = new Product();
            for (Product prod : product.productList) {
                isProdInList = 0;
                if (prodName.equalsIgnoreCase(prod.productName)) {
                    productIndex = i;
                    isProdInList++;
                    product.displayProductInfo("A(Z) " + prod.productName + " NEVŰ TERMÉK ADATAI:", prod.productName, prod.productCode, prod.unitPrice, prod.availableQuantity);
                    if (prod.availableQuantity == 0) {
                        System.out.println(RED.getColorCode() + "\nA termék megtalálható a nyilvántartásban, de jelenleg nincs készleten. Válassz mást, kérlek!" + RESET.getColorCode());
                        localAvailableQuantity = prod.availableQuantity;
                    }
                    break;
                }
                i++;
            }
            if (isProdInList == 0) {
                System.out.print(RED.getColorCode() + "\n\"" + prodName + "\"" + " nevű termék nem szerepel az árukészletben, kérlek válassz másik terméket:" + RESET.getColorCode() + " ");
                for (Product item : product.productList) {
                    System.out.print(item.getProductName() + ", ");
                }
                System.out.println();
            }
        } while (isProdInList == 0 || localAvailableQuantity == 0);
        return prodName;
    }

    private int getQuantity(String prodName) {
        int quantity = 0;
        Product product = new Product();
        while (product.productList.get(productIndex).getAvailableQuantity() - quantity < 0 || quantity < 1) {
            ErrorHandling errorHandling = new ErrorHandling();
            quantity = errorHandling.validNumberPls("\nKérem az eladandó mennyiséget:");
            if (product.productList.get(productIndex).getAvailableQuantity() - quantity < 0) {
                System.out.println(RED.getColorCode() + "\nÖsszesen " + product.productList.get(productIndex).getAvailableQuantity() + " " + prodName + " van készleten, ennél többet nem lehet eladni belőle!" + RESET.getColorCode());
            }
            if (quantity < 1)
                System.out.println(RED.getColorCode() + "\nAz eladott mennyiségnek legalább 1-nek lennie kell!" + RESET.getColorCode());
        }
        return quantity;
    }

    private boolean customerRegistration(String custName) {
        customerIndex = 0;
        boolean isRegisteredCustomer = false;
        Customer customer = new Customer();
        for (Customer cust : customer.customerList) {
            if (cust.customerName.equalsIgnoreCase(custName)) {
                isRegisteredCustomer = true;
                break;
            }
            customerIndex++;
        }
        return isRegisteredCustomer;
    }

    private void transactionRegistration(boolean isRegisteredCustomer, String custName, String prodName, int quantity) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss");
        String formatDateTime = nowDateTime.format(customFormat);
        String customerId;
        ParentEntity parentEntity = new ParentEntity();
        Product product = new Product();
        if (isRegisteredCustomer) {
            Customer.customerList.get(customerIndex).setTotalPurchases(Customer.customerList.get(customerIndex).getTotalPurchases() + product.productList.get(productIndex).getUnitPrice() * quantity);
            customerId = Customer.customerList.get(customerIndex).getCustomerID();
        } else {
            Customer customerObjectAsSellProduct;
            customerId = "cID" + parentEntity.generateId();
            customerObjectAsSellProduct = new Customer(custName, product.productList.get(productIndex).getUnitPrice() * quantity, customerId);
            Customer.customerList.add(customerObjectAsSellProduct);
        }
        SalesTransaction salesTransactionObject = new SalesTransaction();
        salesTransactionObject.setTransactionId("trId" + parentEntity.generateId());
        salesTransactionObject.setCustomer(custName);
        salesTransactionObject.setCustId(customerId);
        salesTransactionObject.setProduct(prodName);
        salesTransactionObject.setQuantitySold(quantity);
        salesTransactionObject.setUnitPrice(product.productList.get(productIndex).getUnitPrice());
        salesTransactionObject.setTransactionDate(formatDateTime);
        System.out.println();
        salesTransactionObject.displayTransactionInfo(productIndex, isRegisteredCustomer);
        SalesTransaction.transactionList.add(salesTransactionObject);
    }
    //-----1.MENÜPONT VÉGE-----//


    //-----2.MENÜPONT-----//
    // A készletfeltöltés folyamatának metódusa
    public void restockProduct(String text) {
        String productName;
        int unitPrice;
        int availableQuantity = 0;
        System.out.println(text);
        String isAnotherAddOrRemove;
        ErrorHandling errorHandling = new ErrorHandling();
        do {
            do {
                Utils utils = new Utils();
                productName = utils.readFromUser("Kérem a termék nevét:");
                errorHandling.validName(productName);
            } while (productName.length() < 3 || productName.contains(","));
            boolean yON = isListContainsProduct(productName);
            if (yON) {
                Product product = new Product();
                unitPrice = errorHandling.isResultPositiveNumber();
                availableQuantity = getQuantityForRestock();
                ParentEntity parentEntity = new ParentEntity();
                String prId = "pr" + parentEntity.generateId();
                Product productObjectAsRestock = new Product(productName, prId, unitPrice, availableQuantity);
                product.productList.add(productObjectAsRestock);
                productObjectAsRestock.setProductName(productName);
                productObjectAsRestock.setUnitPrice(unitPrice);
                productObjectAsRestock.setAvailableQuantity(availableQuantity);
                productObjectAsRestock.displayProductInfoAfterRestock("\nA TERMÉK HOZZÁADÁSRA KERÜLT AZ ÁRUKÉSZLETHEZ:");
            }
            isAnotherAddOrRemove = errorHandling.answerYoN("Szeretnél újabb terméket hozzáadni, vagy meglévőnek a darabszámát módosítani? (I/N)");
            System.out.println();
        } while (!isAnotherAddOrRemove.equalsIgnoreCase("N"));
    }

    private boolean isListContainsProduct(String productName) {
        boolean yON = true;
        int i = 0;
        Product product = new Product();
        for (Product prod : product.productList) {
            if (productName.equalsIgnoreCase(prod.productName)) {
                Product.displayProductInfo("A(Z) " + productName + " NEVŰ TERMÉK MÁR SZEREPEL A NYILVÁNTARTÁSBAN. ADATAI:", productName, prod.productCode, prod.unitPrice, prod.availableQuantity);
                addAndRemoveProduct(prod.productName, i);
                yON = false;
                break;
            }
            i++;
        }
        return yON;
    }

    private int getQuantityForRestock() {
        int availableQuantity;
        do {
            ErrorHandling errorHandling = new ErrorHandling();
            availableQuantity = errorHandling.validNumberPls("\nKérem a termék darabszámát:");
            if (availableQuantity < 1) {
                System.out.println(RED.getColorCode() + "\nA termék darabszámának legalább 1-nek kell lennie!" + RESET.getColorCode());
            }
        } while (availableQuantity < 1);
        return availableQuantity;
    }

    private void addAndRemoveProduct(String productName, int i) {
        String userAnswerForAddAndRemove;
        ErrorHandling errorHandling = new ErrorHandling();
        userAnswerForAddAndRemove = errorHandling.answerYoNoOrDelete("Szeretnél hozzáadni vagy elvenni a termékből (I/N)? A nyilvántartásból való törléshez nyomd meg a \"T\"-t!");
        Product product = new Product();
        if (userAnswerForAddAndRemove.equalsIgnoreCase("I")) {
            String searchAgain;
            do {
                howManyItemAddOrRemove(productName, i);
                searchAgain = errorHandling.answerYoN("Szeretnéd még a(z) " + productName + " darabszámát módosítani? (I/N)");
            } while (!searchAgain.equalsIgnoreCase("N"));
        } else if (userAnswerForAddAndRemove.equalsIgnoreCase("T")) {
            String deleteConfirmation = errorHandling.answerYoN(RED.getColorCode() + "Biztosan TÖRÖLNI szeretnéd a(z) " + product.productList.get(i).getProductName() + " nevű terméket a nyilvántartásból? (I/N)" + RESET.getColorCode());
            if (deleteConfirmation.equalsIgnoreCase("I")) {
                product.productList.remove(product.productList.get(i));
                System.out.println(System.lineSeparator() + "Az elem törlésre került!");
            }
        }
    }

    private void howManyItemAddOrRemove(String productName, int i) {
        int quantity;
        int userPiece;
        ErrorHandling errorHandling = new ErrorHandling();
        Product product = new Product();
        do {
            if (product.productList.get(i).getAvailableQuantity() != 0) {
                userPiece = errorHandling.validNumberPls("\nMennyivel növeljük, vagy csökkentsük a(z) " + productName + " darabszámát?");
            } else {
                userPiece = errorHandling.validNumberPls("\nMennyivel növeljük a(z) " + productName + " darabszámát?");
            }
            quantity = product.productList.get(i).getAvailableQuantity();
            if (quantity + userPiece < 0) {
                System.out.println(RED.getColorCode() + "\nNem lehet egy termékből 0-nál kevesebb!" + RESET.getColorCode());
            }
        } while (quantity + userPiece < 0);
        product.productList.get(i).setAvailableQuantity(quantity + userPiece);
        System.out.println("\nA módosítás megtörtént!");
        Product.displayProductInfo("A TERMÉK ADATAI A BEVÉTELEZÉSI TRANZAKCIÓ UTÁN:", productName, product.productList.get(i).getProductCode(), product.productList.get(i).getUnitPrice(), product.productList.get(i).getAvailableQuantity());
    }
    //-----2.MENÜPONT VÉGE-----//


    //-----3.MENÜPONT-----//
    // Megjeleníti az összes terméket a raktárban.
    public void displayProductList(String text) {
        System.out.println(text);
        Product product = new Product();
        if (product.productList.isEmpty()) {
            System.out.println("Jelenleg nincs termék a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + product.productList.size() + " tétel szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Product item : product.productList) {
                System.out.println("Név: " + item.getProductName() + ", Ár: " + item.getUnitPrice() + " HUF, Mennyiség: " + item.getAvailableQuantity() + ", Termékkód: " + item.getProductCode());
            }
        }
        System.out.println();
    }
    //-----3.MENÜPONT VÉGE-----//


    //-----4.MENÜPONT-----//
    // Megjeleníti az összes ügyfelet.
    public void displayCustomerList(String text) {
        System.out.println(text);
        if (Customer.customerList.isEmpty()) {
            System.out.println("Jelenleg nincs regisztrált ügyfél a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + Customer.customerList.size() + " regisztrált ügyfél szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Customer item : Customer.customerList) {
                System.out.println("Név: " + item.getCustomerName() + " (" + item.getCustomerID() + "), Összes vásárlás: " + item.getTotalPurchases() + " HUF");
            }
        }
        System.out.println();
    }
    //-----4.MENÜPONT VÉGE-----//


    //-----5.MENÜPONT-----//
    // Megjeleníti az összes tranzakciót.
    public void displayTransactionList(String text) {
        System.out.println(text);
        if (SalesTransaction.transactionList.isEmpty()) {
            System.out.println("Jelenleg nincs tranzakció a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + SalesTransaction.transactionList.size() + " tranzakció szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (SalesTransaction item : SalesTransaction.transactionList) {
                System.out.println(item.getTransactionId() + ", Dátum: " + item.getTransactionDate() + ", Termék: " + item.getProduct() + ", Mennyiség: " + item.getQuantitySold() + ", Fizetett összeg: " + item.getQuantitySold() + " * " + item.getUnitPrice() + " = " + (item.getQuantitySold() * item.getUnitPrice()) + " HUF, Vásárló: " + item.getCustomer() + " (" + item.getCustId() + ")");
            }
        }
        System.out.println();
    }
    //-----5.MENÜPONT VÉGE-----//


    //-----6.MENÜPONT-----//
    // Adatok fájlba írása.
    public void saveDataToFile() {
        // Az árukészlet fájlba írása
        String productListContent = "";
        Product product = new Product();
        for (Product item : product.productList) {
            productListContent = productListContent + item.getProductName() + "," + item.getProductCode() + "," + item.getUnitPrice() + "," + item.getAvailableQuantity() + System.lineSeparator();
        }
        Utils utils = new Utils();
        utils.writeToFile(productListContent, "resources/inventoryList.txt");

        // A tranzakciók fájlba írása
        String transactionListContent = "";
        for (SalesTransaction item : SalesTransaction.transactionList) {
            transactionListContent = transactionListContent + item.getTransactionId() + "," + item.getTransactionDate() + "," + item.getProduct() + "," + item.getQuantitySold() + "," + item.getUnitPrice() + "," + item.getCustomer() + "," + item.getCustId() + System.lineSeparator();
        }
        utils.writeToFile(transactionListContent, "resources/transactionList.txt");

        // Az ügyfelek fájlba írása
        String customerListContent = "";
        for (Customer item : Customer.customerList) {
            customerListContent = customerListContent + item.getCustomerName() + "," + item.getTotalPurchases() + "," + item.getCustomerID() + System.lineSeparator();
        }
        utils.writeToFile(customerListContent, "resources/customerList.txt");
        System.out.println("Az adatok elmentésre kerültek...");
    }
    //-----6.MENÜPONT VÉGE-----//


    //-----ADATOK FÁJLBÓL LISTÁBA OLVASÁSA-----//
    // Termékek fájlból olvasása
    public static void loadProductsFromFile() {
        System.out.print("Checking the database of products : ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/inventoryList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String prod = rowData[0];
            String id = rowData[1];
            int price = Integer.parseInt(rowData[2]);
            int quantity = Integer.parseInt(rowData[3]);
            Product product = new Product(prod, id, price, quantity);
            Product.productList.add(product);
        }
    }

    // Ügyféladatok fájlból olvasása
    public static void loadCustomersFromFile() {
        System.out.print("Checking the database of customers: ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/customerList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String customerName = rowData[0];
            int totalPurchase = Integer.parseInt(rowData[1]);
            String customerID = rowData[2];
            Customer customer = new Customer(customerName, totalPurchase, customerID);
            Customer.customerList.add(customer);
        }
    }

    // Tranzakcióadatok fájlból olvasása
    public static void loadTransactionsFromFile() {
        System.out.print("Checking the database of transactions : ");
        Utils utils = new Utils();
        List<String> result = utils.readFromFile("src/main/resources/transactionList.txt");
        for (String row : result) {
            String[] rowData = row.split(",");
            String transactionId = rowData[0];
            String transactionDate = rowData[1];
            String product = rowData[2];
            int quantitySold = Integer.parseInt(rowData[3]);
            int unitPrice = Integer.parseInt(rowData[4]);
            String customer = rowData[5];
            String custId = rowData[6];
            SalesTransaction salesTransaction = new SalesTransaction(transactionId, product, customer, custId, quantitySold, unitPrice, transactionDate);
            SalesTransaction.transactionList.add(salesTransaction);
        }
    }
    //-----ADATOK FÁJLBÓL LISTÁBA OLVASÁSÁNAK VÉGE-----//
}
