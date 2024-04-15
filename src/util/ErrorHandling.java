package util;

import static util.Colors.*;

public class ErrorHandling {

    public int validNumberPls(String question) {
        boolean validNum;
        int validNumber = 0;
        do {
            try {
                Utils utils = new Utils();
                validNumber = Integer.parseInt(utils.readFromUser(question));
                validNum = true;
            } catch (NumberFormatException e) {
                System.out.println(RED.getColorCode() + "Valós számot kérek!" + RESET.getColorCode());
                validNum = false;
            }
        } while (!validNum);
        return validNumber;
    }

    public String answerYoN(String question) {
        String userAnswerYoN;
        do {
            Utils utils = new Utils();
            userAnswerYoN = utils.readFromUser(System.lineSeparator() + question);
            if (!userAnswerYoN.equalsIgnoreCase("I") && !userAnswerYoN.equalsIgnoreCase("N")) {
                System.out.println(RED.getColorCode() + "IGEN vagy NEM választ kérek!" + RESET.getColorCode());
            }
        } while (!userAnswerYoN.equalsIgnoreCase("I") && !userAnswerYoN.equalsIgnoreCase("N"));
        return userAnswerYoN;
    }

    public String answerYoNoOrDelete(String question) {
        String userAnswerYoN;
        do {
            Utils utils = new Utils();
            userAnswerYoN = utils.readFromUser(System.lineSeparator() + question);
            if (!userAnswerYoN.equalsIgnoreCase("I") && !userAnswerYoN.equalsIgnoreCase("N") && !userAnswerYoN.equalsIgnoreCase("T")) {
                System.out.println(RED.getColorCode() + "IGEN/NEM, vagy TÖRLÉS választ kérek!" + RESET.getColorCode());
            }
        } while (!userAnswerYoN.equalsIgnoreCase("I") && !userAnswerYoN.equalsIgnoreCase("N") && !userAnswerYoN.equalsIgnoreCase("T"));
        return userAnswerYoN;
    }

    public int isResultPositiveNumber() {
        int unitPrice;
        do {
            ErrorHandling errorHandling = new ErrorHandling();
            unitPrice = errorHandling.validNumberPls("\nKérem a termék árát:");
            if (unitPrice < 0) {
                System.out.println(RED.getColorCode() + "A termék értéke nem lehet mínusz összeg!" + RESET.getColorCode());
            }
        } while (unitPrice < 0);
        return unitPrice;
    }

    public void validName(String name) {
        if (name.length() < 3) {
            System.out.println(RED.getColorCode() + "A névnek minimum 3 karakternek kell lennie!\n" + RESET.getColorCode());
        }
        if (name.contains(","))
            System.out.println(RED.getColorCode() + "A név nem tartalmazhat \",\" karaktert!\n" + RESET.getColorCode());
    }
}

