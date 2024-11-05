package iventory.util;

public class ValidationUtils {

    public static boolean isValidName(String customerName) {
        return customerName.length() >= 3 && !customerName.contains(",");
    }
}
