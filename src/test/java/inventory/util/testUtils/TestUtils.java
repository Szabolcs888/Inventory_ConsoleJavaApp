package inventory.util.testUtils;

import inventory.dataStorage.CustomerRepository;
import inventory.dataStorage.ProductRepository;
import inventory.dataStorage.SalesTransactionRepository;

public class TestUtils {

    public static void reset() {
        ProductRepository.clearProductData();
        CustomerRepository.clearCustomerData();
        SalesTransactionRepository.clearSalesTransactionData();
    }
}
