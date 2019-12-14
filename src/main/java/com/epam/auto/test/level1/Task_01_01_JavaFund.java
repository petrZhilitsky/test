package com.epam.auto.test.level1;

import java.util.Arrays;
import java.util.Scanner;

public class Task_01_01_JavaFund {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter arr lenght");
		int num = sc.nextInt();
		int[] arr = new int[num];
		System.out.println("Enter arr values");
		for (int i = 0; i < num; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println("Arr: " + Arrays.toString(arr));
		
		//1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину
		int[] arrLen = arrLen(arr);
		arr = sortUpArr(arr);
		arrLen = sortUpArr(arrLen);
		System.out.println("min value=" + arr[0] + ", max value=" + arr[arr.length - 1]);
		System.out.println("min length value=" + arrLen[0] + ", max length value=" + arrLen[arrLen.length - 1]);
		
		//2. Вывести числа в порядке возрастания (убывания) значений их длины
		System.out.println("Arr sorted min->max: " + Arrays.toString(arr));
		arr = sortDownArr(arr);
		arrLen = sortDownArr(arrLen);
		System.out.println("Arr sorted max->min: " + Arrays.toString(arr));

		//3. Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину
		double mid = middle(arrLen);
		System.out.printf("Values|Length (less than middle length %.2f)\n", mid);
		for (int i = 0; i < arr.length; i++) {
			if ((double) arrLen[i] < mid) {
				System.out.println(arr[i] + " | " + arrLen[i]);
			}
		}
		System.out.printf("Values|Length (bigger than middle length %.2f)\n", mid);
		for (int i = 0; i < arr.length; i++) {
			if ((double) arrLen[i] > mid) {
				System.out.println(arr[i] + " | " + arrLen[i]);
			}
		}
		
		//4. Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них
		int[] arrDifferent = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int[] arrDigits = arrDigits(arr[i], arrLen[i]);
			int count = 0;
			for (int j = 0; j < arrDigits.length; j++) {
				for (int k = j + 1; k < arrDigits.length; k++) {
					if (arrDigits[j] != arrDigits[k]) {
						count ++;
					}
				}
			}
			arrDifferent[i] = count;
		}
		int minI = 0;
		for (int i = 1; i < arrDifferent.length; i++) {
			if (arrDifferent[i] < arrDifferent[minI]) {
				minI = i;
			}
		}
		System.out.println("Min number of different digits: " + arr[minI]);

		//5. Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр
		int even, odd;
		int onlyEvens = 0;
		int halfEvensOdds = 0;
		for (int i = 0; i < arr.length; i++) {
			int[] digits = arrDigits(arr[i], arrLen[i]);
			even = odd = 0;
			for (int j = 0; j < digits.length; j++) {
				if (digits[j] % 2 == 0) {
					even ++;
				} else {
					odd ++;
				}
			}
			if (even == odd) {
				halfEvensOdds++;
			} else if(odd == 0) {
				onlyEvens++;
			}
		}
		System.out.println(onlyEvens + " elments with only evens");
		System.out.println(halfEvensOdds + " elements with half evens/odds");

		//6. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них
		int risedItem = 0;
		for (int i = 0; i < arr.length; i++) {
			int[] digits = arrDigits(arr[i], arrLen[i]);
			int rise = 0;
			for (int j = digits.length - 2; j >= 0; j--) {
				if (digits[j] > digits[j + 1]) {
					rise++;
				}
			}
			if (rise + 1 == arrLen[i]) {
				risedItem = arr[i];
				break;
			}
		}
		System.out.println("First number with digits in order of increasing: " + risedItem);

		//7. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них
		int onlyDiff = -1;
		for (int i = 0; i < arr.length; i++) {
			int[] digits = arrDigits(arr[i], arrLen[i]);
			for (int j = 1; j < digits.length; j++) {
				if (digits[j] != digits[j - 1]) {
					onlyDiff = arr[i];
					break;
				}
			}
			if(onlyDiff != -1) {
				break;
			}
		}
		System.out.println("First number with all different digits: " + onlyDiff);
		
		sc.close();
	}
	
	public static int[] arrLen(int arr[]) {
		int[] arrLen = new int[arr.length];
		int count;
		int num;
		for (int i = 0; i < arr.length; i++) {
			count = 0;
			num = arr[i];
			do {
				num /= 10;
				count++;
			} while (num != 0);
			arrLen[i] = count;
		}
		return arrLen;
	}
	
	public static int[] sortUpArr(int arr[]) {
		int arrNew[] = arr;
		int x;
		for (int j = 0; j < arrNew.length - 1; j++) {
			for (int i = 0; i < arrNew.length - 1 - j; i++) {
				if(arrNew[i] > arrNew[i + 1]) {
					x = arrNew[i];
					arrNew[i] = arrNew[i + 1];
					arrNew[i + 1] = x;
				}
			}	
		}
		return arrNew;
	}
	
	public static int[] sortDownArr(int arr[]) {
		int arrNew[] = arr;
		int x;
		for (int j = 0; j < arrNew.length - 1; j++) {
			for (int i = 0; i < arrNew.length - 1 - j; i++) {
				if(arrNew[i] < arrNew[i + 1]) {
					x = arrNew[i];
					arrNew[i] = arrNew[i + 1];
					arrNew[i + 1] = x;
				}
			}	
		}
		return arrNew;
	}
	
	public static double middle(int arr[]) {
		double mid;
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		mid = (double) sum / arr.length;
		return mid;
	}
	
	public static int[] arrDigits(int num, int count) {
		int[] digits = new int[count];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = num % 10;
			num /= 10;
		}
		return digits;
	}
}
