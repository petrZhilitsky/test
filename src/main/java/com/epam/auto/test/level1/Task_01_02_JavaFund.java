package com.epam.auto.test.level1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Task_01_02_JavaFund {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the matrix size 'n' (matrix[n][n])");
		int n = sc.nextInt();
		int[][] matrix = new int[n][n];
		System.out.println("Enter the values interval 'm' (-m -> m)");
		int m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = ThreadLocalRandom.current().nextInt(m * (-1), m + 1);
				//matrix[i][j] = (int) (Math.random() * ((m - m * (-1)) + 1)) + m * (-1);
			}
		}
		show(matrix, n);

		//1. Упорядочить строки (столбцы) матрицы в порядке возрастания значений элементов k-го столбца (строки)
		System.out.println("Choose row for sorting min->max:");
		int x = sc.nextInt();
		while(x < 1 || x > n){
			System.out.println("Error. Try again:");
			x = sc.nextInt();
		}
		int val = matrix[x - 1][0];
		for (int j = 0; j < n; j++) {
			for (int i = 1; i < n; i++) {
				if (matrix[x - 1][i] < matrix[x - 1][i - 1]) {
					val = matrix[x - 1][i];
					matrix[x - 1][i] = matrix[x - 1][i - 1];
					matrix[x - 1][i - 1] = val;
				}
			}
		}
		System.out.println("Row " + x +" sorted min->max:");
		show(matrix, n);

		//2. Найти и вывести наибольшее число возрастающих (убывающих) элементов матрицы, идущих подряд
		int currentCount = 1;
		int count = 0;
		int k = 0;
		ArrayList<Integer> currentVals = new ArrayList<>();
		Integer[] vals = new Integer[0];
		int[] matrArr = new int[n * n];
		for(int i = 0; i < n ; i++){
			for(int j = 0 ; j < n; j++){
				matrArr[k] = matrix[i][j];
				k++;
			}
		}
		currentVals.add(matrArr[0]);
		for (int i = 1; i < matrArr.length ; i++) {
			if(matrArr[i - 1] > matrArr[i]){
				currentCount++;
				currentVals.add(matrArr[i]);
				if (currentCount > count) {
					count = currentCount;
					vals = currentVals.toArray(new Integer[currentVals.size()]);;
				}
			} else {
				currentCount = 0;
				currentVals.clear();
				currentVals.add(matrArr[i]);
			}
		}
		System.out.println("The longest number of values in order of decreasing: ");
		for (int j : vals) {
			System.out.print(j + " ");
		}
		System.out.println();

		//3. Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки
		currentVals.clear();
		currentCount = -1;
		count = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (matrix[i][j] > 0) {
					if (currentCount == -1) {
						currentCount = j;
					} else if (count == -1) {
						count = j;
					} else {
						break;
					}
				}
			}
			if (currentCount != -1 && count != -1 && count > currentCount) {
				int sum = 0;
				for (int j =  currentCount + 1; j < count; j++) {
					sum += matrix[i][j];
				}
				currentVals.add(sum);
			} else {
				currentVals.add(0);
			}
			currentCount = -1;
			count = -1;
		}
		System.out.println("The sum of values between first and second positive elems in rows: ");
		for (int i : currentVals) {
			System.out.print(i + " ");
		}
		System.out.println();

		//4. Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие
		int[][] matrTemp;
		int max = matrix[0][0];
		ArrayList<Integer> maxI = new ArrayList<>();
		ArrayList<Integer> maxJ = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] > max) {
					max = matrix[i][j];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == max) {
					if (!maxI.contains(i)) {
						maxI.add(i);
					}
					if (!maxJ.contains(j)) {
						maxJ.add(j);
					}
				}
			}
		}
		matrTemp = new int[n - maxI.size()][n - maxJ.size()];
		int i1 = -1;
		int j1 = -1;
		for (int i = 0; i < n; i++) {
			if (!maxI.contains(i)) {
				++i1;
				for (int j = 0; j < n; j++) {
					if (!maxJ.contains(j)) {
						matrTemp[i1][++j1] = matrix[i][j];
					}
					if (j1 == n - maxJ.size() - 1) {
						break;
					}
				}
			}
			j1 = -1;
			if (i1 == n - maxI.size() - 1) {
				break;
			}
		}
		matrix = matrTemp;
		System.out.println("Deleted rows and columns contained max value " + max + ":");
		for (i1 = 0; i1 < n - maxI.size(); i1++) {
			for (j1 = 0; j1 < n - maxJ.size(); j1++) {
				System.out.printf("%4d ", matrTemp[i1][j1]);
			}
			System.out.println();
		}
		
		sc.close();
	}
	
	public static void show(int[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%4d ", matrix[i][j]);
			}
			System.out.println();
		}
	}
}