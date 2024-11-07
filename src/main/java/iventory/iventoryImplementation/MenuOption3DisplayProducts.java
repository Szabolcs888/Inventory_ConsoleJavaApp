package iventory.iventoryImplementation;

import iventory.dataStorage.ProductRepository;
import iventory.iventoryEntities.Product;
import iventory.util.displayHelpers.ProductDisplayHelper;

import java.util.List;

public class MenuOption3DisplayProducts {

    public void displayProductList(String text) {
        List<Product> productList = ProductRepository.getProductList();
        ProductDisplayHelper.displayProductList(productList, text);
    }
}