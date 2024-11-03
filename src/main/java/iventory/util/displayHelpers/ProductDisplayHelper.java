package iventory.util.displayHelpers;

import iventory.dataStorage.ProductRepository;
import iventory.iventoryEntities.Product;

import java.util.List;

import static iventory.util.Colors.*;

public class ProductDisplayHelper {

    public static void displayProductList(List<Product> productList, String text) {
        System.out.println(text);
        if (productList.isEmpty()) {
            System.out.println("Jelenleg nincs termék a nyilvántartásban!");
        } else {
            System.out.println(GREEN.getColorCode() + "Összesen " + productList.size() + " tétel szerepel a nyilvántartásban:" + RESET.getColorCode());
            for (Product item : productList) {
                System.out.println("Név: " + item.getProductName() + ", Ár: " + item.getUnitPrice() + " HUF, Mennyiség: " + item.getQuantity() + ", Termékkód: " + item.getProductId());
            }
        }
        System.out.println();
    }

    public static void displayProductInfo(String text, String productName, String productId, int unitPrice, int quantity) {
        System.out.println(GREEN.getColorCode() + "\n" + text + RESET.getColorCode());
        System.out.println("Termék neve: " + productName +
                ", Termékazonosító kód: " + productId +
                ", Egységár: " + unitPrice + " HUF" +
                ", Elérhető mennyiség: " + quantity);
    }

    public static void displayProductInfoAfterGoodsReceipt(Product newProduct) {
        System.out.println(GREEN.getColorCode() + "\nA TERMÉK HOZZÁADÁSRA KERÜLT AZ ÁRUKÉSZLETHEZ:" + RESET.getColorCode());
        System.out.println("Termék neve: " + newProduct.productName +
                "\nTermékazonosító kód: " + newProduct.productId +
                "\nEgységár: " + newProduct.unitPrice + " HUF" +
                "\nElérhető mennyiség: " + newProduct.quantity);
    }

    public static void displayProductNotFoundMessage(String productName, List<Product> productList) {
        System.out.print(RED.getColorCode() + "\n\"" + productName + "\" nevű termék nem szerepel az árukészletben, kérlek válassz másik terméket:" + RESET.getColorCode() + " ");
        for (Product item : productList) {
            System.out.print(item.getProductName() + ", ");
        }
        System.out.println();
    }

    public static void displayProductQuantityErrorMessage(int productIndex, String productName, int availableQuantity, int quantitySold) {
        if (availableQuantity - quantitySold < 0) {
            System.out.println(RED.getColorCode() + "\nÖsszesen " + ProductRepository.getProductList().get(productIndex).getQuantity() + " " + productName + " van készleten, ennél többet nem lehet eladni belőle!" + RESET.getColorCode());
        }
        if (quantitySold < 1)
            System.out.println(RED.getColorCode() + "\nAz eladott mennyiségnek legalább 1-nek lennie kell!" + RESET.getColorCode());
    }

    public static void displayProductInfoIfProductFound(Product product) {
        displayProductInfo("A(Z) " + product.getProductName() + " NEVŰ TERMÉK ADATAI:",
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
        displayProductInfo(
                "A(Z) " + inputProductName + " NEVŰ TERMÉK MÁR SZEREPEL A NYILVÁNTARTÁSBAN. ADATAI:",
                inputProductName, product.productId, product.unitPrice, product.quantity);
    }

    public static void displayOutOfStockMessage() {
        System.out.println(RED.getColorCode() + "\nA termék megtalálható a nyilvántartásban, de jelenleg nincs készleten. Válassz mást, kérlek!" + RESET.getColorCode());
    }

    public static void displayNoProductsAvailableMessage() {
        System.out.println("Jelenleg nincs eladható termék!\n");
    }
}

