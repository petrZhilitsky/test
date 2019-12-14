package com.epam.auto.test.level1;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Task_06_JavaIO {

	public static void main(String[] args) throws InterruptedException {
		File path = new File("D:/Music/!German rock/");
		File outputFile = new File("src/main/java/com/epam/auto/test/level1/files/output.txt");
		
		try (PrintStream printStream = new PrintStream(outputFile)){
			printStream.println(path.getName());
			List<File> files = new ArrayList<>();
			if(path.isDirectory()) {
				for(File item : path.listFiles()) {
					if(item.isDirectory()) {
						printStream.println("|----" + item.getName());
						File subDir = item;
						List<File> subFiles = new ArrayList<>();
						for (File subDirFile : subDir.listFiles()) {
							if (subDirFile.isDirectory()) {
								printStream.println("|    |----" + subDirFile.getName());
								File subSubDir = subDirFile;
								List<File> subSubFiles = new ArrayList<>();
								for (File subSubDirFile : subSubDir.listFiles()) {
									if (subSubDirFile.isDirectory()) {
										printStream.println("|    |    |----" + subSubDirFile.getName());
									} else {
										subSubFiles.add(subSubDirFile);
									}
								}
								for (File file : subSubFiles) {
									printStream.println("|    |    |----" + file.getName());
								}
							} else {
								subFiles.add(subDirFile);
							}
						}
						for (File file : subFiles) {
							printStream.println("|    |----" + file.getName());
						}
					} else {
						files.add(item);
					}
				}
				for (File file : files) {
					printStream.println("|----" + file.getName());
				}
			} else {
				//TODO
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}