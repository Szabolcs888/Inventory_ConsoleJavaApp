package inventory.inventoryImplementation;

import inventory.dataStorage.ProductRepository;
import inventory.inventoryEntities.Product;
import inventory.util.displayHelpers.ProductDisplayHelper;

import java.util.List;

public class MenuOption3DisplayProducts {

    public void displayProductList(String text) {
        List<Product> productList = ProductRepository.getProductList();
        ProductDisplayHelper.displayProductList(productList, text);
    }
}