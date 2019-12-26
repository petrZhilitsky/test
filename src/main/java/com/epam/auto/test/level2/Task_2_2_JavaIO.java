package com.epam.auto.test.level2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_2_2_JavaIO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the absolute path to the folder or file");
        String path = scanner.next();
        File inputFile = new File(path);
        File outputFile = new File("src/main/java/com/epam/auto/test/level2/files/output_main.txt");

        if (inputFile.isDirectory()) {
            try (PrintStream printStream = new PrintStream(outputFile)) {
                printStream.println("[" + inputFile.getName() + "]");
            } catch (Exception e) {
                e.printStackTrace();
            }
            printDir(inputFile, outputFile, "");
        }
        if (inputFile.isFile()) {
            printFile(inputFile);
        }
    }

    private static void printDir(File inputFile, File outputFile, String spaceDelimiter) {
        spaceDelimiter += "    |";
        String lineDelimiter = spaceDelimiter + "----";
        List<File> dirList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();

        //separating folders from files
        for (File file : inputFile.listFiles()) {
            if (file.isDirectory()) {
                dirList.add(file);
            }
            if (file.isFile()) {
                fileList.add(file);
            }
        }

        //writing child folders
        for (File file : dirList) {
            try (FileWriter fileWriter = new FileWriter(outputFile, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(spaceDelimiter + "\r");
                bufferedWriter.write(lineDelimiter + "[" + file.getName() + "]\r");
            } catch (Exception e) {
                e.printStackTrace();
            }
            printDir(file, outputFile, spaceDelimiter);
        }

        try (FileWriter fileWriter = new FileWriter(outputFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(spaceDelimiter + "\r");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //writing child files
        for (File file : fileList) {
            try (FileWriter fileWriter = new FileWriter(outputFile, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(lineDelimiter + file.getName() + "\r");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printFile(File inputFile) {
        try (Scanner scanner = new Scanner(new FileReader(inputFile))) {
            int dirCount = 0;
            int fileCount = 0;
            int filesLength = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("----[")) {
                    dirCount++;
                } else if (line.contains("|----")) {
                    fileCount++;
                    int from = line.indexOf("|----") + 5;
                    int to = line.lastIndexOf(".");
                    filesLength += line.substring(from, to).length();
                }
            }

            System.out.println("Total number of folders: " + dirCount);
            System.out.println("Total number of files: " + fileCount);
            System.out.printf("Average number of files in folder: %.2f\n", fileCount / (double) dirCount);
            System.out.printf("Average file name length: %.2f", filesLength / (double) fileCount);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }
}