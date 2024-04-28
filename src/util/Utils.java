package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.Colors.*;
import static util.Colors.RESET;

public class Utils {

    //-----Adatok beolvasása konzolról-----//
    public String readFromUser(String question) {
        Scanner scan = new Scanner(System.in);
        System.out.println(question);
        return scan.nextLine().trim();
    }

    //-----Adatok beolvasása fájlból-----//
    public List<String> readFromFile (String path) {
        List<String> result = new ArrayList<>();
        try {
            Charset charset = Charset.forName("ISO-8859-1");
            result = Files.readAllLines(Paths.get(path), charset);
            System.out.println(GREEN.getColorCode() + "OK.." + RESET.getColorCode());
        } catch (
                IOException e) {
            System.out.println(RED.getColorCode() + "COULD NOT BE FOUND!" + RESET.getColorCode());
        }
        return result;
    }

    //-----Adatok fájlba írása-----//
    public void writeToFile(String content, String path) {
        try {
            new File("resources").mkdirs();
            Files.write(Paths.get(path), content.getBytes("ISO-8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
