package iventory.iventoryImplementation;

import iventory.dataStorage.CustomerRepository;
import iventory.dataStorage.ProductRepository;
import iventory.dataStorage.SalesTransactionRepository;
import iventory.iventoryEntities.Customer;
import iventory.iventoryEntities.Product;
import iventory.iventoryEntities.SalesTransaction;
import iventory.util.*;
import iventory.util.displayHelpers.ProductDisplayHelper;
import iventory.util.displayHelpers.TransactionDisplayHelper;

import java.util.List;

public class MenuOption1Sell {
    int customerIndex = 0;
    int productIndex = 0;

    public void sellProduct(String text) {
        System.out.println(text);
        String askAnotherSell;
        do {
            if (!ProductRepository.getProductList().isEmpty()) {
                processSale();
                ProductDisplayHelper.displayProductInfoAfterSellAndUpdateGoodsReceipt(productIndex, "PRODUCT DATA AFTER THE TRANSACTION:");
                askAnotherSell = ErrorHandler.getYesOrNoAnswer("Would you like to register another sale? (Y/N)");
                System.out.println();
            } else {
                ProductDisplayHelper.displayNoProductsAvailableMessage();
                askAnotherSell = "N";
            }
        } while (askAnotherSell.equalsIgnoreCase("Y"));
    }

    private void processSale() {
        String customerName = getCustomerName();
        String productName = getProductName();
        int quantitySold = getQuantitySold(productName);
        boolean isRegisteredCustomer = isCustomerRegistered(customerName);
        String customerId = customerRegistration(isRegisteredCustomer, customerName, quantitySold);
        transactionRegistration(isRegisteredCustomer, customerName, customerId, productName, quantitySold);
        updateQuantity(quantitySold);
    }

    private void updateQuantity(int quantitySold) {
        ProductRepository.getProductList().get(productIndex).setQuantity(
                ProductRepository.getProductList().get(productIndex).getQuantity() - quantitySold);
    }

    private String getCustomerName() {
        String customerName;
        do {
            customerName = UserInputUtils.readFromUser("Please enter the customer's name:");
            ErrorHandler.validateName(customerName);
        } while (!ValidationUtils.isValidName(customerName));
        return customerName;
    }

    private String getProductName() {
        String inputProductName;
        boolean isProductInList;
        boolean isAvailableQuantityZero;
        do {
            isAvailableQuantityZero = false;
            inputProductName = UserInputUtils.readFromUser("\nPlease enter the name of the product to be sold:");
            productIndex = 0;
            List<Product> productList = ProductRepository.getProductList();
            Product foundProduct = findProductByName(productList, inputProductName);
            if (foundProduct != null) {
                ProductDisplayHelper.displayProductInfoIfProductFound(foundProduct);
                isProductInList = true;
                if (foundProduct.getQuantity() == 0) {
                    ProductDisplayHelper.displayOutOfStockMessage();
                    isAvailableQuantityZero = true;
                }
            } else {
                isProductInList = false;
                ProductDisplayHelper.displayProductNotFoundMessage(inputProductName, productList);
            }
        } while (!isProductInList || isAvailableQuantityZero);
        return inputProductName;
    }

    private Product findProductByName(List<Product> productList, String productName) {
        for (Product product : productList) {
            if (productName.equalsIgnoreCase(product.getProductName())) {
                return product;
            }
            productIndex++;
        }
        return null;
    }

    private int getQuantitySold(String productName) {
        int quantitySold = 0;
        int availableQuantity = ProductRepository.getProductList().get(productIndex).getQuantity();
        while (availableQuantity - quantitySold < 0 || quantitySold < 1) {
            quantitySold = ErrorHandler.getValidNumber("\nPlease enter the quantity to be sold:");
            ProductDisplayHelper.displayProductQuantityErrorMessage(productIndex, productName, availableQuantity, quantitySold);
        }
        return quantitySold;
    }

    private boolean isCustomerRegistered(String customerName) {
        List<Customer> customerList = CustomerRepository.getCustomerList();
        Customer foundCustomer = findCustomerByName(customerList, customerName);
        return foundCustomer != null;
    }

    private Customer findCustomerByName(List<Customer> customerList, String customerName) {
        customerIndex = 0;
        for (Customer customer : customerList) {
            if (customer.getCustomerName().equalsIgnoreCase(customerName)) {
                return customer;
            }
            customerIndex++;
        }
        return null;
    }

    private String customerRegistration(boolean isRegisteredCustomer, String customerName, int quantitySold) {
        if (isRegisteredCustomer) {
            return updateRegisteredCustomerPurchases(quantitySold);
        } else {
            return registerNewCustomer(customerName, quantitySold);
        }
    }

    private String updateRegisteredCustomerPurchases(int quantitySold) {
        Customer customer = CustomerRepository.getCustomerList().get(customerIndex);
        Product product = ProductRepository.getProductList().get(productIndex);
        customer.setTotalPurchases(customer.getTotalPurchases() + product.getUnitPrice() * quantitySold);
        return customer.getCustomerId();
    }

    private String registerNewCustomer(String customerName, int quantitySold) {
        String customerId = "cID" + IdUtils.generateId();
        Product product = ProductRepository.getProductList().get(productIndex);
        Customer newCustomer = new Customer(customerName, customerId, product.getUnitPrice() * quantitySold);
        CustomerRepository.addCustomer(newCustomer);
        return customerId;
    }

    private void transactionRegistration(boolean isRegisteredCustomer, String customerName, String customerId, String productName, int quantitySold) {
        int unitPrice = ProductRepository.getProductList().get(productIndex).getUnitPrice();
        String transactionId = "trId" + IdUtils.generateId();
        String transactionDate = DateUtils.getCurrentFormattedDate();
        SalesTransaction salesTransaction = new SalesTransaction(
                transactionId, customerName, customerId, productName, quantitySold, unitPrice, transactionDate);
        System.out.println();
        TransactionDisplayHelper.displayTransactionInfo(
                productIndex, productName, quantitySold, customerName, customerId, isRegisteredCustomer, transactionDate);
        SalesTransactionRepository.addSalesTransaction(salesTransaction);
    }
}
