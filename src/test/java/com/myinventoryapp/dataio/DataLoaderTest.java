package com.myinventoryapp.dataio;

import com.myinventoryapp.datastorage.ProductRepository;
import com.myinventoryapp.inventoryentities.Product;
import com.myinventoryapp.util.FileUtils;
import com.myinventoryapp.util.testutils.TestFilePaths;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DataLoaderTest {

    @Test
    void testLoadAllDataCallsGetters() {
        try (MockedStatic<FilePaths> mockFilePaths = Mockito.mockStatic(FilePaths.class)) {
            mockFilePaths.when(FilePaths::getProductsFilePath)
                    .thenReturn("src/main/resources/inventorydata/productList.txt");
            mockFilePaths.when(FilePaths::getCustomersFilePath)
                    .thenReturn("src/main/resources/inventorydata/customerList.txt");
            mockFilePaths.when(FilePaths::getTransactionsFilePath)
                    .thenReturn("src/main/resources/inventorydata/transactionList.txt");

            DataLoader dataLoader = spy(new DataLoader());
            doNothing().when(dataLoader).loadProductsFromFile(anyString());
            doNothing().when(dataLoader).loadCustomersFromFile(anyString());
            doNothing().when(dataLoader).loadTransactionsFromFile(anyString());

            dataLoader.loadAllData();

            // Verify that the FilePaths getters were called (this is the additional verification)
            mockFilePaths.verify(FilePaths::getProductsFilePath, times(1));
            mockFilePaths.verify(FilePaths::getCustomersFilePath, times(1));
            mockFilePaths.verify(FilePaths::getTransactionsFilePath, times(1));
        }
    }

    @Test
    void testLoadAllDataCallsFileLoaders() {
        DataLoader dataLoader = spy(new DataLoader());
        doNothing().when(dataLoader).loadProductsFromFile(anyString());
        doNothing().when(dataLoader).loadCustomersFromFile(anyString());
        doNothing().when(dataLoader).loadTransactionsFromFile(anyString());

        dataLoader.loadAllData();

        // Assert: Verify that the load methods are called with the correct arguments
        verify(dataLoader, times(1)).loadProductsFromFile(
                "src/main/resources/inventorydata/productList.txt");
        verify(dataLoader, times(1)).loadCustomersFromFile(
                "src/main/resources/inventorydata/customerList.txt");
        verify(dataLoader, times(1)).loadTransactionsFromFile(
                "src/main/resources/inventorydata/transactionList.txt");
    }

    @Test
    void testLoadProductsFromFilePrintsMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String testFilePath = TestFilePaths.getTestProductsFilePath();
        try (MockedStatic<FileUtils> mockFileUtils = Mockito.mockStatic(FileUtils.class)) {
            mockFileUtils.when(() -> FileUtils.readFromFile(testFilePath))
                    .thenReturn(Collections.emptyList());

            DataLoader dataLoader = new DataLoader();
            dataLoader.loadProductsFromFile(testFilePath);

            String expectedMessage = "Checking the database of products :";
            String output = outputStream.toString();
            assertTrue(output.contains(expectedMessage),
                    "Expected message '" + expectedMessage + "' was not found in the output.");
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    void testLoadProductsFromFile_ReadFromFileCalled() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String testFilePath = TestFilePaths.getTestProductsFilePath();
        try (MockedStatic<FileUtils> mockFileUtils = Mockito.mockStatic(FileUtils.class)) {
            mockFileUtils.when(() -> FileUtils.readFromFile(testFilePath))
                    .thenReturn(Arrays.asList("apple,pr5197140,560,25", "pear,pr4270613,675,19"));

            DataLoader dataLoader = new DataLoader();
            dataLoader.loadProductsFromFile(testFilePath);

            mockFileUtils.verify(() -> FileUtils.readFromFile(testFilePath), times(1));
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    void testLoadProductsFromFile_DataAddedToProductRepository() {
        ProductRepository.clearProductList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String testFilePath = TestFilePaths.getTestProductsFilePath();
        List<String> testFileData = Arrays.asList(
                "apple,pr5197140,560,25",
                "pear,pr4270613,675,19",
                "lemon,pr6634365,880,43"
        );

        try (MockedStatic<FileUtils> mockedFileUtils = Mockito.mockStatic(FileUtils.class)) {
            mockedFileUtils.when(() -> FileUtils.readFromFile(testFilePath))
                    .thenReturn(testFileData);

            DataLoader dataLoader = new DataLoader();
            dataLoader.loadProductsFromFile(testFilePath);

            List<String> productListAsStringFromProductRepository = new ArrayList<>();
            for (Product item : ProductRepository.getProductList()) {
                productListAsStringFromProductRepository.add(item.getProductName() + "," + item.getProductId() + "," + item.getUnitPrice() +
                        "," + item.getQuantity());
            }

            assertEquals(testFileData, productListAsStringFromProductRepository);
        } finally {
            System.setOut(System.out);
            ProductRepository.clearProductList();
        }
    }
}



