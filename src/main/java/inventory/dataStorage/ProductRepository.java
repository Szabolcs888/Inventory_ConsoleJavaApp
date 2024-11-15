package inventory.dataStorage;

import inventory.inventoryEntities.Product;

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

    public static void clearProductData() {
        productList.clear();
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
