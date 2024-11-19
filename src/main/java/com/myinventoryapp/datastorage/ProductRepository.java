package com.myinventoryapp.datastorage;

import com.myinventoryapp.inventoryentities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static List<Product> productList = new ArrayList<>();

    public static void addProduct(Product product) {
        productList.add(product);
    }

    public static void deleteProduct(int productIndex) {
        productList.remove(productIndex);
    }

    public static void clearProductList() {
        productList.clear();
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
