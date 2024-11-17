package com.myinventoryapp.inventoryimplementation;

import com.myinventoryapp.inventoryentities.Product;
import com.myinventoryapp.util.*;
import com.myinventoryapp.util.displayhelpers.ProductDisplayHelper;
import com.myinventoryapp.datastorage.ProductRepository;

import java.util.List;

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
            askAddOrModifyProduct = ErrorHandler.getYesOrNoAnswer(
                    "Would you like to add a new product or modify the quantity of an existing one? (Y/N)");
            System.out.println();
        } while (!askAddOrModifyProduct.equalsIgnoreCase("N"));
    }

    private String getProductName() {
        String inputProductName;
        do {
            inputProductName = UserInputUtils.readFromUser("Please enter the product name:");
            ErrorHandler.validateName(inputProductName);
        } while (!ValidationUtils.isValidName(inputProductName));
        return inputProductName;
    }

    private void addNewProduct(String productName) {
        String productId = "pr" + IdUtils.generateId();
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
                "Would you like to add to or subtract from the product quantity? (Y/N) To delete from the inventory, press \"D\"!");
        if (askAddOrRemoveProduct.equalsIgnoreCase("Y")) {
            modifyProductQuantityLoop(productName, productIndex);
        } else if (askAddOrRemoveProduct.equalsIgnoreCase("D")) {
            deleteProduct(productIndex);
        }
    }

    private void modifyProductQuantityLoop(String productName, int productIndex) {
        String modifyProductAnswerAgain;
        do {
            updateProductQuantity(productName, productIndex);
            modifyProductAnswerAgain = ErrorHandler.getYesOrNoAnswer(
                    "Would you like to modify the " + productName + " quantity further? (Y/N)");
        } while (!modifyProductAnswerAgain.equalsIgnoreCase("N"));
    }

    private void deleteProduct(int productIndex) {
        String productNameForDeletion = ProductRepository.getProductList().get(productIndex).productName;
        String deleteConfirmation = ErrorHandler.getYesOrNoAnswer(
                Colors.RED.getColorCode() + "Are you sure you want to DELETE the product named " +
                        productNameForDeletion + " from the inventory? (Y/N)" + Colors.RESET.getColorCode());
        if (deleteConfirmation.equalsIgnoreCase("Y")) {
            ProductRepository.deleteProduct(productIndex);
            System.out.println("\nThe item has been deleted!");
        }
    }

    private int getProductPrice() {
        int unitPrice;
        do {
            unitPrice = ErrorHandler.getValidNumber("\nPlease enter the product price:");
            ErrorHandler.validatePrice(unitPrice);
        } while (unitPrice < 0);
        return unitPrice;
    }

    private int getProductQuantity() {
        int quantity;
        do {
            quantity = ErrorHandler.getValidNumber("\nPlease enter the product quantity:");
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
                productIndex, "PRODUCT INFORMATION AFTER RECEIPT TRANSACTION:");
    }

    private int getQuantityModification(String productName, int productIndex) {
        if (ProductRepository.getProductList().get(productIndex).quantity != 0) {
            return ErrorHandler.getValidNumber("\nBy how much should we increase or decrease the " + productName + " quantity?");
        } else {
            return ErrorHandler.getValidNumber("\nBy how much should we increase the " + productName + " quantity?");
        }
    }

    private int calculateNewQuantity(int productIndex, int quantityModification) {
        int quantity = ProductRepository.getProductList().get(productIndex).quantity;
        return quantity + quantityModification;
    }

    private void setNewQuantity(int productIndex, int newQuantity) {
        ProductRepository.getProductList().get(productIndex).setQuantity(newQuantity);
        System.out.println("\nThe modification has been made!");
    }
}
