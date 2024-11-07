package iventory.util.displayHelpers;

import iventory.dataStorage.ProductRepository;
import iventory.iventoryEntities.Product;

import java.util.List;

import static iventory.util.Colors.*;

public class ProductDisplayHelper {

    public static void displayProductList(List<Product> productList, String text) {
        System.out.println(text);
        if (productList.isEmpty()) {
            System.out.println("There are currently no products in the inventory!");
        } else {
            System.out.println(GREEN.getColorCode() + "There are a total of " + productList.size() +
                    " items in the inventory:" + RESET.getColorCode());
            for (Product item : productList) {
                System.out.println("Name: " + item.getProductName() + ", Price: " + item.getUnitPrice() +
                        " HUF, Quantity: " + item.getQuantity() + ", Product ID: " + item.getProductId());
            }
        }
        System.out.println();
    }

    public static void displayProductInfo(String text, String productName, String productId, int unitPrice, int quantity) {
        System.out.println(GREEN.getColorCode() + "\n" + text + RESET.getColorCode());
        System.out.println("Product name: " + productName +
                ", Product ID: " + productId +
                ", Unit price: " + unitPrice + " HUF" +
                ", Available quantity: " + quantity);
    }

    public static void displayProductInfoAfterGoodsReceipt(Product newProduct) {
        System.out.println(GREEN.getColorCode() + "\nTHE PRODUCT HAS BEEN ADDED TO THE INVENTORY:" + RESET.getColorCode());
        System.out.println("Product name: " + newProduct.productName +
                "\nProduct ID: " + newProduct.productId +
                "\nUnit price: " + newProduct.unitPrice + " HUF" +
                "\nAvailable quantity: " + newProduct.quantity);
    }

    public static void displayProductNotFoundMessage(String productName, List<Product> productList) {
        System.out.print(RED.getColorCode() + "\nThe product named \"" + productName +
                "\" is not in the inventory, please choose another product:" + RESET.getColorCode() + " ");
        for (Product item : productList) {
            System.out.print(item.getProductName() + ", ");
        }
        System.out.println();
    }

    public static void displayProductQuantityErrorMessage(int productIndex, String productName, int availableQuantity, int quantitySold) {
        if (availableQuantity - quantitySold < 0) {
            System.out.println(RED.getColorCode() + "\nThere are a total of " +
                    ProductRepository.getProductList().get(productIndex).getQuantity() + " " +
                    productName + " in stock, you cannot sell more than that!" + RESET.getColorCode());
        }
        if (quantitySold < 1)
            System.out.println(RED.getColorCode() + "\nThe quantity sold must be at least 1!" + RESET.getColorCode());
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
                inputProductName, product.productId, product.unitPrice, product.quantity);
    }

    public static void displayOutOfStockMessage() {
        System.out.println(RED.getColorCode() + "\nThe product is in the inventory but currently out of stock." +
                " Please choose another!" + RESET.getColorCode());
    }

    public static void displayNoProductsAvailableMessage() {
        System.out.println("There are currently no products available for sale!\n");
    }
}

