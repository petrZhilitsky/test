package com.epam.auto.test.level2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task_2_2_JavaIO_01_optional {

    public static void main(String[] args) {
        Scanner scanner = null;
        List<Integer> listNums = new ArrayList<>();
        final String INPUT_FILE = "src/main/java/com/epam/auto/test/level2/files/2_2_01_optional.txt";
        final String OUTPUT_FILE = "src/main/java/com/epam/auto/test/level2/files/output_optionals.txt";
        try (Reader read = new FileReader(INPUT_FILE); Writer writer = new FileWriter(OUTPUT_FILE)){
            scanner = new Scanner(read);
            while (scanner.hasNextLine()) {
                listNums.add(scanner.nextInt());
            }
            Collections.sort(listNums);
            for (Integer num : listNums) {
                writer.write(num + "\n");
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