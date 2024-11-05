package iventory.util;

import java.util.Random;

public class IdUtils {

    public static int generateId() {
        Random random = new Random();
        return random.nextInt(1000000, 9999999) + 1;
    }
}
