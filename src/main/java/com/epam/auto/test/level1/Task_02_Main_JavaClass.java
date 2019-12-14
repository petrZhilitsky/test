package com.epam.auto.test.level1;

import java.time.Year;
import java.util.ArrayList;

import com.epam.auto.test.level1.claasses.Car;

public class Task_02_Main_JavaClass {

	public static void main(String[] args) {
		ArrayList<Car> cars = new ArrayList<>();
		cars.add(new Car("BMW", "X5", 2010, "black", 15000, "8585AA3"));
		cars.add(new Car("Audi", "80", 2000, "grey", 5000, "8745EE3"));
		cars.add(new Car());
		cars.add(new Car("Lada", "Vesta", 2015, "wheat", 10000, "9514BA3"));
		cars.add(new Car("Ford", "Mustang", "red", 30000));
		cars.add(new Car("GAZ", "ZIM", 1961, "black", 45000, "A123BC777"));
		cars.add(new Car("Audi", "Q7", "blue", 32000));
		cars.add(new Car("Lada", "Granta", 2016, "white", 10000, "9514BA3"));
		cars.add(new Car("Toyota", "Camry", 2010, "coral", 16000, "8569BA3"));

		showCars(cars);
		nameCars(cars, "lada");
		nameCars(cars, "audi", 10);
		yearPriceCars(cars, 2010, 10000);
	}
	
	public static void showCars(ArrayList<Car> cars) {
		System.out.println("All cars in the list:");
		for (Car car : cars) {
			System.out.println(car.toString());
		}
	}
	
	public static void nameCars(ArrayList<Car> cars, String name) {
		System.out.println("Cars which have name like '" + name + "':");
		for (Car car : cars) {
			if (car.getName() != null && car.getName().equalsIgnoreCase(name)) {
				System.out.println(car.toString());
			}
		}
	}
	
	public static void nameCars(ArrayList<Car> cars, String name, int years) {
		System.out.println("Cars which have name like '" + name + "' and are used more than " + years + " years:");
		for (Car car : cars) {
			if (car.getName() != null && car.getName().equalsIgnoreCase(name) && (Year.now().getValue() - car.getYear()) > years) {
				System.out.println(car.toString());
			}
		}
	}
	
	public static void yearPriceCars(ArrayList<Car> cars, int year, double cost) {
		System.out.println("Cars which were manufactured in the year " + year + " and have cost more than " + cost + ":");
		for (Car car : cars) {
			if (car.getName() != null && car.getYear() == year && car.getCost() > cost) {
				System.out.println(car.toString());
			}
		}
	}
}