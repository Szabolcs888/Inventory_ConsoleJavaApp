package com.myinventoryapp;

import com.myinventoryapp.inventoryimplementation.*;
import com.myinventoryapp.util.ErrorHandler;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InventoryAppTest {

    @Test
    public void testMenuSelectionValidInput() {
        String expectedMessage = "You can choose from the following menu items:";

        String simulatedInput = "3\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InventoryApp inventoryApp = new InventoryApp();
        int result = inventoryApp.menuSelection(expectedMessage);

        assertEquals(3, result, "Expected menu selection to be '3', but got: " + result);

        String output = outputStream.toString();
        assertTrue(output.contains(expectedMessage),
                "Expected message '" + expectedMessage + "' was not found in the output.");
    }

    @Test
    public void testMenuSelectionInvalidInput() {
        try (MockedStatic<ErrorHandler> mockErrorHandler = Mockito.mockStatic(ErrorHandler.class)) {
            mockErrorHandler.when(() -> ErrorHandler.getValidNumber(anyString()))
                    .thenReturn(0)
                    .thenReturn(3);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            InventoryApp inventoryApp = new InventoryApp();
            inventoryApp.menuSelection("You can choose from the following menu items:");

            String output = outputStream.toString();
            assertTrue(output.contains("You can choose from 1 to 6!"),
                    "Expected message 'You can choose from 1 to 6!' was not found in the output.");
        }
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption1Sell() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption1Sell mockSellMenu = mock(MenuOption1Sell.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption1Sell(mockSellMenu);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(1);

        verify(mockSellMenu, times(1)).sellProduct(anyString());
        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption2GoodsReceipt() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption2GoodsReceipt mockGoodsReceipt = mock(MenuOption2GoodsReceipt.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption2GoodsReceipt(mockGoodsReceipt);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(2);

        verify(mockGoodsReceipt, times(1)).goodsReceipt(anyString());
        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption3DisplayProducts() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption3DisplayProducts mockDisplayProducts = mock(MenuOption3DisplayProducts.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption3DisplayProducts(mockDisplayProducts);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(3);

        verify(mockDisplayProducts, times(1)).displayProductList(anyString());
        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption4DisplayCustomers() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption4DisplayCustomers mockDisplayCustomers = mock(MenuOption4DisplayCustomers.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption4DisplayCustomers(mockDisplayCustomers);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(4);

        verify(mockDisplayCustomers, times(1)).displayCustomerList(anyString());
        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption5DisplayTransactions() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption5DisplayTransactions mockDisplayTransactions = mock(MenuOption5DisplayTransactions.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption5DisplayTransactions(mockDisplayTransactions);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(5);

        verify(mockDisplayTransactions, times(1)).displayTransactionList(anyString());
        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorExecutesMenuOption6SaveData() {
        InventoryApp inventoryApp = new InventoryApp();

        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption6SaveData(mockSaveData);

        inventoryApp.transactionSelector(6);

        verify(mockSaveData, times(1)).saveData();
    }

    @Test
    public void testTransactionSelectorWithMenuSelection() {
        InventoryApp inventoryApp = Mockito.spy(new InventoryApp());

        MenuOption1Sell mockSellMenu = mock(MenuOption1Sell.class);
        MenuOption6SaveData mockSaveData = mock(MenuOption6SaveData.class);
        inventoryApp.setMenuOption1Sell(mockSellMenu);
        inventoryApp.setMenuOption6SaveData(mockSaveData);
        doReturn(6).when(inventoryApp).menuSelection(anyString());

        inventoryApp.transactionSelector(1);

        verify(inventoryApp, times(1)).menuSelection(anyString());
        verify(mockSellMenu, times(1)).sellProduct(anyString());
        verify(mockSaveData, times(1)).saveData();
    }
}