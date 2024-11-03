package iventory.iventoryImplementation;

import iventory.dataStorage.ProductRepository;
import iventory.iventoryEntities.Product;
import iventory.util.ErrorHandler;
import iventory.util.displayHelpers.ProductDisplayHelper;
import iventory.util.Utils;

import java.util.List;

import static iventory.util.Colors.*;

public class MenuOption2GoodsReceipt {

    public void goodsReceipt(String text) {
        System.out.println(text);
        String inputProductName;
        String askAddOrModifyProduct;
        do {
            inputProductName = getProductName();
            if (!isProductInList(inputProductName)) {
                addNewProduct(inputProductName);
            }
            askAddOrModifyProduct = ErrorHandler.getYesOrNoAnswer("Szeretnél újabb terméket hozzáadni, vagy meglévőnek a darabszámát módosítani? (I/N)");
            System.out.println();
        } while (!askAddOrModifyProduct.equalsIgnoreCase("N"));
    }

    private String getProductName() {
        String inputProductName;
        do {
            inputProductName = Utils.readFromUser("Kérem a termék nevét:");
            ErrorHandler.validateName(inputProductName);
        } while (!Utils.isValidName(inputProductName));
        return inputProductName;
    }

    private void addNewProduct(String productName) {
        String productId = "pr" + Utils.generateId();
        int unitPrice = getProductPrice();
        int quantity = getProductQuantity();
        Product newProduct = new Product(productName, productId, unitPrice, quantity);
        ProductRepository.addProduct(newProduct);
        ProductDisplayHelper.displayProductInfoAfterGoodsReceipt(newProduct);
    }

    private boolean isProductInList(String inputProductName) {
        List<Product> productList = ProductRepository.getProductList();
        for (int productIndex = 0; productIndex < productList.size(); productIndex++) {
            Product product = productList.get(productIndex);
            if (inputProductName.equalsIgnoreCase(product.productName)) {
                ProductDisplayHelper.displayExistingProductInfo(product, inputProductName);
                askUserForProductAction(product.productName, productIndex);
                return true;
            }
        }
        return false;
    }

    private void askUserForProductAction(String productName, int productIndex) {
        String askAddOrRemoveProduct = ErrorHandler.getYesOrNoOrDeleteAnswer(
                "Szeretnél hozzáadni vagy elvenni a termékből (I/N)? A nyilvántartásból való törléshez nyomd meg a \"T\"-t!");
        if (askAddOrRemoveProduct.equalsIgnoreCase("I")) {
            modifyProductQuantityLoop(productName, productIndex);
        } else if (askAddOrRemoveProduct.equalsIgnoreCase("T")) {
            deleteProduct(productIndex);
        }
    }

    private void modifyProductQuantityLoop(String productName, int productIndex) {
        String modifyProductAnswerAgain;
        do {
            updateProductQuantity(productName, productIndex);
            modifyProductAnswerAgain = ErrorHandler.getYesOrNoAnswer(
                    "Szeretnéd még a(z) " + productName + " darabszámát módosítani? (I/N)");
        } while (!modifyProductAnswerAgain.equalsIgnoreCase("N"));
    }

    private void deleteProduct(int productIndex) {
        String productNameForDeletion = ProductRepository.getProductList().get(productIndex).productName;
        String deleteConfirmation = ErrorHandler.getYesOrNoAnswer(
                RED.getColorCode() + "Biztosan TÖRÖLNI szeretnéd a(z) " + productNameForDeletion + " nevű terméket a nyilvántartásból? (I/N)" + RESET.getColorCode());
        if (deleteConfirmation.equalsIgnoreCase("I")) {
            ProductRepository.getProductList().remove(ProductRepository.getProductList().get(productIndex));
            System.out.println("\nAz elem törlésre került!");
        }
    }

    private int getProductPrice() {
        int unitPrice;
        do {
            unitPrice = ErrorHandler.getValidNumber("\nKérem a termék árát:");
            ErrorHandler.validatePrice(unitPrice);
        } while (unitPrice < 0);
        return unitPrice;
    }

    private int getProductQuantity() {
        int quantity;
        do {
            quantity = ErrorHandler.getValidNumber("\nKérem a termék darabszámát:");
            ErrorHandler.validateQuantity(quantity);
        } while (quantity < 1);
        return quantity;
    }

    private void updateProductQuantity(String productName, int productIndex) {
        int newQuantity;
        do {
            int quantityModification = getQuantityModification(productName, productIndex);
            newQuantity = calculateNewQuantity(productIndex, quantityModification);
            ErrorHandler.validateNonNegativeQuantity(newQuantity);
        } while (newQuantity < 0);
        setNewQuantity(productIndex, newQuantity);
        ProductDisplayHelper.displayProductInfoAfterSellAndUpdateGoodsReceipt(
                productIndex, "A TERMÉK ADATAI A BEVÉTELEZÉSI TRANZAKCIÓ UTÁN:");
    }

    private int getQuantityModification(String productName, int productIndex) {
        if (ProductRepository.getProductList().get(productIndex).quantity != 0) {
            return ErrorHandler.getValidNumber("\nMennyivel növeljük, vagy csökkentsük a(z) " + productName + " darabszámát?");
        } else {
            return ErrorHandler.getValidNumber("\nMennyivel növeljük a(z) " + productName + " darabszámát?");
        }
    }

    private int calculateNewQuantity(int productIndex, int quantityModification) {
        int quantity = ProductRepository.getProductList().get(productIndex).quantity;
        return quantity + quantityModification;
    }

    private void setNewQuantity(int productIndex, int newQuantity) {
        ProductRepository.getProductList().get(productIndex).setQuantity(newQuantity);
        System.out.println("\nA módosítás megtörtént!");
    }
}
