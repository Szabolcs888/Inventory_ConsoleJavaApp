package iventoryEntities;

import java.util.Random;

public class ParentEntity {
    public int generateId() {
        Random random = new Random();
        return random.nextInt(1000000, 9999999) + 1;
    }

    String toFileString() {
        return null;
    }
}
