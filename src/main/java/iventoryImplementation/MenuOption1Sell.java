package main.java.iventoryImplementation;

import main.java.iventoryEntities.Customer;
import main.java.iventoryEntities.ParentEntity;
import main.java.iventoryEntities.Product;
import main.java.iventoryEntities.SalesTransaction;
import main.java.util.ErrorHandling;
import main.java.util.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static main.java.util.Colors.RED;
import static main.java.util.Colors.RESET;

public class MenuOption1Sell {
    private int productIndex = 0;
    private int customerIndex = 0;

    // A termék eladásának folyamatát kezelő metódus
    public void sellProduct(String text) {
        System.out.println(text);
        String isAnotherSearch;
        Product product = new Product();
        do {
            if (!product.productList.isEmpty()) {
                String custName = getCustomerName();
                String prodName = getProductName();
                int quantity = getQuantity(prodName);
                boolean isRegisteredCustomer = customerRegistration(custName);
                transactionRegistration(isRegisteredCustomer, custName, prodName, quantity);
                product.productList.get(productIndex).setAvailableQuantity(product.productList.get(productIndex).getAvailableQuantity() - quantity);
                product.displayProductInfo("A TERMÉK ADATAI A TRANZAKCIÓ UTÁN:", product.productList.get(productIndex).productName, product.productList.get(productIndex).productCode, product.productList.get(productIndex).unitPrice, product.productList.get(productIndex).availableQuantity);
                ErrorHandling errorHandling = new ErrorHandling();
                isAnotherSearch = errorHandling.answerYoN("Szeretnél újabb termékeladást bejegyezni? (I/N)");
                System.out.println();
            } else {
                System.out.println("Jelenleg nincs eladható termék!\n");
                isAnotherSearch = "N";
            }
        } while (Objects.requireNonNull(isAnotherSearch).equalsIgnoreCase("I"));
    }

    private String getCustomerName() {
        String custName;
        do {
            Utils utils = new Utils();
            custName = utils.readFromUser("Kérem a vásárló nevét:");
            ErrorHandling errorHandling = new ErrorHandling();
            errorHandling.validName(custName);
        } while (custName.length() < 3 || custName.contains(","));
        return custName;
    }

    private String getProductName() {
        String prodName;
        int isProdInList = 0;
        int localAvailableQuantity;
        do {
            localAvailableQuantity = 1;
            Utils utils = new Utils();
            prodName = utils.readFromUser("\nKérem az eladandó termék nevét:");
            int i = 0;
            Product product = new Product();
            for (Product prod : product.productList) {
                isProdInList = 0;
                if (prodName.equalsIgnoreCase(prod.productName)) {
                    productIndex = i;
                    isProdInList++;
                    product.displayProductInfo("A(Z) " + prod.productName + " NEVŰ TERMÉK ADATAI:", prod.productName, prod.productCode, prod.unitPrice, prod.availableQuantity);
                    if (prod.availableQuantity == 0) {
                        System.out.println(RED.getColorCode() + "\nA termék megtalálható a nyilvántartásban, de jelenleg nincs készleten. Válassz mást, kérlek!" + RESET.getColorCode());
                        localAvailableQuantity = prod.availableQuantity;
                    }
                    break;
                }
                i++;
            }
            if (isProdInList == 0) {
                System.out.print(RED.getColorCode() + "\n\"" + prodName + "\"" + " nevű termék nem szerepel az árukészletben, kérlek válassz másik terméket:" + RESET.getColorCode() + " ");
                for (Product item : product.productList) {
                    System.out.print(item.getProductName() + ", ");
                }
                System.out.println();
            }
        } while (isProdInList == 0 || localAvailableQuantity == 0);
        return prodName;
    }

    private int getQuantity(String prodName) {
        int quantity = 0;
        Product product = new Product();
        while (product.productList.get(productIndex).getAvailableQuantity() - quantity < 0 || quantity < 1) {
            ErrorHandling errorHandling = new ErrorHandling();
            quantity = errorHandling.validNumberPls("\nKérem az eladandó mennyiséget:");
            if (product.productList.get(productIndex).getAvailableQuantity() - quantity < 0) {
                System.out.println(RED.getColorCode() + "\nÖsszesen " + product.productList.get(productIndex).getAvailableQuantity() + " " + prodName + " van készleten, ennél többet nem lehet eladni belőle!" + RESET.getColorCode());
            }
            if (quantity < 1)
                System.out.println(RED.getColorCode() + "\nAz eladott mennyiségnek legalább 1-nek lennie kell!" + RESET.getColorCode());
        }
        return quantity;
    }

    private boolean customerRegistration(String custName) {
        customerIndex = 0;
        boolean isRegisteredCustomer = false;
        Customer customer = new Customer();
        for (Customer cust : customer.customerList) {
            if (cust.customerName.equalsIgnoreCase(custName)) {
                isRegisteredCustomer = true;
                break;
            }
            customerIndex++;
        }
        return isRegisteredCustomer;
    }

    private void transactionRegistration(boolean isRegisteredCustomer, String custName, String prodName, int quantity) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss");
        String formatDateTime = nowDateTime.format(customFormat);
        String customerId;
        ParentEntity parentEntity = new ParentEntity();
        Product product = new Product();
        if (isRegisteredCustomer) {
            Customer.customerList.get(customerIndex).setTotalPurchases(Customer.customerList.get(customerIndex).getTotalPurchases() + product.productList.get(productIndex).getUnitPrice() * quantity);
            customerId = Customer.customerList.get(customerIndex).getCustomerID();
        } else {
            Customer customerObjectAsSellProduct;
            customerId = "cID" + parentEntity.generateId();
            customerObjectAsSellProduct = new Customer(custName, product.productList.get(productIndex).getUnitPrice() * quantity, customerId);
            Customer.customerList.add(customerObjectAsSellProduct);
        }
        SalesTransaction salesTransactionObject = new SalesTransaction();
        salesTransactionObject.setTransactionId("trId" + parentEntity.generateId());
        salesTransactionObject.setCustomer(custName);
        salesTransactionObject.setCustId(customerId);
        salesTransactionObject.setProduct(prodName);
        salesTransactionObject.setQuantitySold(quantity);
        salesTransactionObject.setUnitPrice(product.productList.get(productIndex).getUnitPrice());
        salesTransactionObject.setTransactionDate(formatDateTime);
        System.out.println();
        salesTransactionObject.displayTransactionInfo(productIndex, isRegisteredCustomer);
        SalesTransaction.transactionList.add(salesTransactionObject);
    }
}
