package com.myinventoryapp.util.displayhelpers;

import com.myinventoryapp.inventoryentities.Product;
import com.myinventoryapp.util.Colors;
import com.myinventoryapp.datastorage.ProductRepository;

import java.util.List;

public class ProductDisplayHelper {

    public static void displayProductList(List<Product> productList, String text) {
        System.out.println(text);
        if (productList.isEmpty()) {
            System.out.println("There are currently no products in the inventory!");
        } else {
            System.out.println(Colors.GREEN.getColorCode() + "There are a total of " + productList.size() +
                    " items in the inventory:" + Colors.RESET.getColorCode());
            for (Product item : productList) {
                System.out.println("Name: " + item.getProductName() + ", Price: " + item.getUnitPrice() +
                        " HUF, Quantity: " + item.getQuantity() + ", Product ID: " + item.getProductId());
            }
        }
        System.out.println();
    }

    private static void displayProductInfo(String text, String productName, String productId, int unitPrice, int quantity) {
        System.out.println(Colors.GREEN.getColorCode() + "\n" + text + Colors.RESET.getColorCode());
        System.out.println("Product name: " + productName +
                ", Product ID: " + productId +
                ", Unit price: " + unitPrice + " HUF" +
                ", Available quantity: " + quantity);
    }

    public static void displayProductInfoAfterGoodsReceipt(Product newProduct) {
        System.out.println(Colors.GREEN.getColorCode() + "\nTHE PRODUCT HAS BEEN ADDED TO THE INVENTORY:" + Colors.RESET.getColorCode());
        System.out.println("Product name: " + newProduct.getProductName() +
                "\nProduct ID: " + newProduct.getProductId() +
                "\nUnit price: " + newProduct.getUnitPrice() + " HUF" +
                "\nAvailable quantity: " + newProduct.getQuantity());
    }

    public static void displayProductNotFoundMessage(String productName, List<Product> productList) {
        System.out.print(Colors.RED.getColorCode() + "\nThe product named \"" + productName +
                "\" is not in the inventory, please choose another product:" + Colors.RESET.getColorCode() + " ");
        for (Product item : productList) {
            System.out.print(item.getProductName() + ", ");
        }
        System.out.println();
    }

    public static void displayProductQuantityErrorMessage(int productIndex, String productName, int availableQuantity, int quantitySold) {
        if (availableQuantity - quantitySold < 0) {
            System.out.println(Colors.RED.getColorCode() + "\nThere are a total of " +
                    ProductRepository.getProductList().get(productIndex).getQuantity() + " " +
                    productName + " in stock, you cannot sell more than that!" + Colors.RESET.getColorCode());
        }
        if (quantitySold < 1)
            System.out.println(Colors.RED.getColorCode() + "\nThe quantity sold must be at least 1!" + Colors.RESET.getColorCode());
    }

    public static void displayProductInfoIfProductFound(Product product) {
        displayProductInfo("DETAILS OF THE PRODUCT NAMED " + product.getProductName() + ":",
                product.getProductName(), product.getProductId(), product.getUnitPrice(), product.getQuantity());
    }

    public static void displayProductInfoAfterSellAndUpdateGoodsReceipt(int productIndex, String text) {
        displayProductInfo(text,
                ProductRepository.getProductList().get(productIndex).getProductName(),
                ProductRepository.getProductList().get(productIndex).getProductId(),
                ProductRepository.getProductList().get(productIndex).getUnitPrice(),
                ProductRepository.getProductList().get(productIndex).getQuantity());
    }

    public static void displayExistingProductInfo(Product product, String inputProductName) {
        displayProductInfo("THE PRODUCT NAMED " + inputProductName + " IS ALREADY IN THE INVENTORY. DETAILS:",
                inputProductName, product.getProductId(), product.getUnitPrice(), product.getQuantity());
    }

    public static void displayOutOfStockMessage() {
        System.out.println(Colors.RED.getColorCode() + "\nThe product is in the inventory but currently out of stock." +
                " Please choose another!" + Colors.RESET.getColorCode());
    }

    public static void displayNoProductsAvailableMessage() {
        System.out.println("There are currently no products available for sale!\n");
    }
}

