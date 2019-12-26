package com.epam.auto.test.level2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_2_2_JavaIO_03_optional {

    public static void main(String[] args) {
        Scanner scanner = null;
        List<String> listStrings = new ArrayList<>();
        final String INPUT_FILE = "src/main/java/com/epam/auto/test/level2/files/2_2_03_optional.java";
        final String OUTPUT_FILE = "src/main/java/com/epam/auto/test/level2/files/output_optionals.java";
        try (Reader read = new FileReader(INPUT_FILE); Writer writer = new FileWriter(OUTPUT_FILE)) {
            scanner = new Scanner(read);
            while (scanner.hasNextLine()) {
                listStrings.add(scanner.nextLine());
            }
            for (String str : listStrings) {
                String newStr = new StringBuffer(str).reverse().toString();
                writer.write(newStr + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
