package inventory.inventoryImplementation;

import inventory.dataIO.DataSaver;

public class MenuOption6SaveData {

    public void saveData() {
        DataSaver.saveAllData();
        System.out.println("Data has been saved..");
    }
}
