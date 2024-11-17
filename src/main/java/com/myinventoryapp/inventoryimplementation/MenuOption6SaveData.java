package com.myinventoryapp.inventoryimplementation;

import com.myinventoryapp.dataio.DataSaver;

public class MenuOption6SaveData {

    public void saveData() {
        DataSaver.saveAllData();
        System.out.println("Data has been saved..");
    }
}
