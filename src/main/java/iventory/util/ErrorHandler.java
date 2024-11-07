package iventory.util;

import static iventory.util.Colors.*;

public class ErrorHandler {

    public static int getValidNumber(String text) {
        boolean isNumberValid;
        int validNumber = 0;
        do {
            try {
                validNumber = Integer.parseInt(UserInputUtils.readFromUser(text));
                isNumberValid = true;
            } catch (NumberFormatException e) {
                System.out.println(RED.getColorCode() + "Please enter a valid number!" + RESET.getColorCode());
                isNumberValid = false;
            }
        } while (!isNumberValid);
        return validNumber;
    }

    public static String getYesOrNoAnswer(String question) {
        String yesOrNoAnswer;
        do {
            yesOrNoAnswer = UserInputUtils.readFromUser("\n" + question);
            if (!yesOrNoAnswer.equalsIgnoreCase("Y") && !yesOrNoAnswer.equalsIgnoreCase("N")) {
                System.out.println(RED.getColorCode() + "Please enter \"YES\" or \"NO\"!" + RESET.getColorCode());
            }
        } while (!yesOrNoAnswer.equalsIgnoreCase("Y") && !yesOrNoAnswer.equalsIgnoreCase("N"));
        return yesOrNoAnswer;
    }

    public static String getYesOrNoOrDeleteAnswer(String question) {
        String yesOrNoOrDeleteAnswer;
        do {
            yesOrNoOrDeleteAnswer = UserInputUtils.readFromUser("\n" + question);
            if (!yesOrNoOrDeleteAnswer.equalsIgnoreCase("Y") &&
                    !yesOrNoOrDeleteAnswer.equalsIgnoreCase("N") &&
                    !yesOrNoOrDeleteAnswer.equalsIgnoreCase("D")) {
                System.out.println(RED.getColorCode() + "Please enter \"YES\", \"NO\", or \"DELETE\"!" + RESET.getColorCode());
            }
        } while (!yesOrNoOrDeleteAnswer.equalsIgnoreCase("Y") &&
                !yesOrNoOrDeleteAnswer.equalsIgnoreCase("N") &&
                !yesOrNoOrDeleteAnswer.equalsIgnoreCase("D"));
        return yesOrNoOrDeleteAnswer;
    }

    public static void validateName(String name) {
        if (name.length() < 3) {
            System.out.println(RED.getColorCode() + "The name must be at least 3 characters long!\n" + RESET.getColorCode());
        }
        if (name.contains(","))
            System.out.println(RED.getColorCode() + "The name cannot contain a \",\" character!\n" + RESET.getColorCode());
    }

    public static void validateQuantity(int quantity) {
        if (quantity < 1) {
            System.out.println(RED.getColorCode() + "\nThe product quantity must be at least 1!" + RESET.getColorCode());
        }
    }

    public static void validateNonNegativeQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println(RED.getColorCode() + "\nThe quantity of a product cannot be less than 0!" + RESET.getColorCode());
        }
    }

    public static void validatePrice(int unitPrice) {
        if (unitPrice < 0) {
            System.out.println(RED.getColorCode() + "The product value cannot be a negative amount!" + RESET.getColorCode());
        }
    }
}
