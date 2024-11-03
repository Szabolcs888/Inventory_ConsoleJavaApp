package iventory.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static iventory.util.Colors.*;

public class Utils {

    public static String readFromUser(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public static List<String> readFromFile(String path) {
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

    public static void writeToFile(String content, String path) {
        try {
            new File("src/main/resources").mkdirs();
            Files.write(Paths.get(path), content.getBytes("ISO-8859-1"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int generateId() {
        Random random = new Random();
        return random.nextInt(1000000, 9999999) + 1;
    }

    public static boolean isValidName(String customerName) {
        return customerName.length() >= 3 && !customerName.contains(",");
    }

    public static String getCurrentFormattedDate() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss");
        return nowDateTime.format(customFormat);
    }
}
