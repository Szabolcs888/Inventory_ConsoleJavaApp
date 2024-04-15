package util;

import java.util.Scanner;

public class Utils {

    //-----ADATOK BEOLVASÁSA KONZOLRÓL-----//
    public String readFromUser(String question) {
        Scanner scan = new Scanner(System.in);
        System.out.println(question);
        return scan.nextLine().trim();
    }
}
