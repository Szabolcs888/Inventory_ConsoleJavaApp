package iventory.dataStorage;

import iventory.iventoryEntities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> productList = new ArrayList<>();

    public static void addProduct(Product product) {
        productList.add(product);
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
