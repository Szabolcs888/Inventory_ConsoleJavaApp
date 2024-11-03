package iventory.util;

import static iventory.util.Colors.*;

public class ErrorHandler {

    public static int getValidNumber(String text) {
        boolean isNumberValid;
        int validNumber = 0;
        do {
            try {
                validNumber = Integer.parseInt(Utils.readFromUser(text));
                isNumberValid = true;
            } catch (NumberFormatException e) {
                System.out.println(RED.getColorCode() + "Valós számot kérek!" + RESET.getColorCode());
                isNumberValid = false;
            }
        } while (!isNumberValid);
        return validNumber;
    }

    public static String getYesOrNoAnswer(String question) {
        String yesOrNoAnswer;
        do {
            yesOrNoAnswer = Utils.readFromUser("\n" + question);
            if (!yesOrNoAnswer.equalsIgnoreCase("I") && !yesOrNoAnswer.equalsIgnoreCase("N")) {
                System.out.println(RED.getColorCode() + "IGEN vagy NEM választ kérek!" + RESET.getColorCode());
            }
        } while (!yesOrNoAnswer.equalsIgnoreCase("I") && !yesOrNoAnswer.equalsIgnoreCase("N"));
        return yesOrNoAnswer;
    }

    public static String getYesOrNoOrDeleteAnswer(String question) {
        String yesOrNoOrDeleteAnswer;
        do {
            yesOrNoOrDeleteAnswer = Utils.readFromUser("\n" + question);
            if (!yesOrNoOrDeleteAnswer.equalsIgnoreCase("I") &&
                    !yesOrNoOrDeleteAnswer.equalsIgnoreCase("N") &&
                    !yesOrNoOrDeleteAnswer.equalsIgnoreCase("T")) {
                System.out.println(RED.getColorCode() + "IGEN/NEM, vagy TÖRLÉS választ kérek!" + RESET.getColorCode());
            }
        } while (!yesOrNoOrDeleteAnswer.equalsIgnoreCase("I") &&
                !yesOrNoOrDeleteAnswer.equalsIgnoreCase("N") &&
                !yesOrNoOrDeleteAnswer.equalsIgnoreCase("T"));
        return yesOrNoOrDeleteAnswer;
    }

    public static void validateName(String name) {
        if (name.length() < 3) {
            System.out.println(RED.getColorCode() + "A névnek minimum 3 karakternek kell lennie!\n" + RESET.getColorCode());
        }
        if (name.contains(","))
            System.out.println(RED.getColorCode() + "A név nem tartalmazhat \",\" karaktert!\n" + RESET.getColorCode());
    }

    public static void validateQuantity(int quantity) {
        if (quantity < 1) {
            System.out.println(RED.getColorCode() + "\nA termék darabszámának legalább 1-nek kell lennie!" + RESET.getColorCode());
        }
    }

    public static void validateNonNegativeQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println(RED.getColorCode() + "\nNem lehet egy termékből 0-nál kevesebb!" + RESET.getColorCode());
        }
    }

    public static void validatePrice(int unitPrice) {
        if (unitPrice < 0) {
            System.out.println(RED.getColorCode() + "A termék értéke nem lehet mínusz összeg!" + RESET.getColorCode());
        }
    }
}
