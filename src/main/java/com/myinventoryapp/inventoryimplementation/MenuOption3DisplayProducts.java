package com.myinventoryapp.inventoryimplementation;

import com.myinventoryapp.inventoryentities.Product;
import com.myinventoryapp.util.displayhelpers.ProductDisplayHelper;
import com.myinventoryapp.datastorage.ProductRepository;

import java.util.List;

public class MenuOption3DisplayProducts {

    public void displayProductList(String text) {
        List<Product> productList = ProductRepository.getProductList();
        ProductDisplayHelper.displayProductList(productList, text);
    }
}