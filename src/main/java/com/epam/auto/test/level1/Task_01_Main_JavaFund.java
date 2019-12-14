package com.epam.auto.test.level1;

import java.time.Month;
import java.util.Scanner;

public class Task_01_Main_JavaFund {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//1. Приветствовать любого пользователя при вводе его имени через командную строку
		System.out.println("Enter the user name:");
		String name = sc.next();
		System.out.println("Hello, " + name + "!");

		//2. Отобразить в окне консоли аргументы командной строки в обратном порядке.
		for (int i = args.length - 1; i >= 0; i--) {
			System.out.print(args[i] + " ");
		}
		System.out.println();

		//3. Вывести заданное количество случайных чисел с переходом и без перехода на новую строку
		System.out.println("Enter the number of random values:");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println((int) (Math.random() * 100));
		}
		for (int i = 0; i < n; i++) {
			System.out.print((int) (Math.random() * 100) + " ");
		}
		System.out.println();

		//4. Ввести целые числа как аргументы командной строки, подсчитать их сумму (произведение) и вывести результат на консоль.
		System.out.println("Enter Integer args:");
		int sum = 0;
		int mult = 1;
		for (String string : args) {
			string = sc.next();
			sum += Integer.parseInt(string);
			mult *= Integer.parseInt(string);
		}
		System.out.println("Sum of args: " + sum + ", product of args: " + mult);

		//5. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. Осуществить проверку корректности ввода чисел.
		System.out.println("Enter value from 1 to 12:");
		int num = sc.nextInt();
		while(num < 1 || num > 12){
			System.out.println("Error. Try again:");
			num = sc.nextInt();
		}
		Month mon = Month.of(num);
		System.out.println("The month is " + mon.toString().toLowerCase());

		sc.close();
	}
}