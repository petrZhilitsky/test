package com.epam.auto.test.level1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task_04_01_JavaColl {

	public static void main(String[] args) {
		try {
			ArrayList<String> stringList = new ArrayList<>();
			Scanner scanner = new Scanner(new File("com/epam/auto/test/level1/files/strings.txt"));
			while (scanner.hasNextLine()) {
				stringList.add(scanner.nextLine());
			}
			System.out.println(stringList);
			FileWriter fileWriter = new FileWriter(new File("com/epam/auto/test/level1/files/strings.txt"));
			for (int i = stringList.size() - 1; i >= 0; i--) {
				System.out.print(stringList.get(i) + " ");
				fileWriter.write(stringList.get(i) + "\n");
			}
			System.out.println();
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}