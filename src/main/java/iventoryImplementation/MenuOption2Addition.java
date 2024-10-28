package main.java.iventoryImplementation;

import main.java.iventoryEntities.ParentEntity;
import main.java.iventoryEntities.Product;
import main.java.util.ErrorHandling;
import main.java.util.Utils;

import static main.java.util.Colors.RED;
import static main.java.util.Colors.RESET;

public class MenuOption2Addition {

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
}
