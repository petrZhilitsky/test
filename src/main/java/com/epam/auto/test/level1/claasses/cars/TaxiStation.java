package com.epam.auto.test.level1.claasses.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaxiStation {
	private List<? extends Car> cars;

	public TaxiStation(List<? extends Car> cars) {
		this.cars = cars;
	}

	public TaxiStation() {
		super();
	}

	public void setCars(List<? extends Car> cars) {
		this.cars = cars;
	}
	
	public double totalCost() {
		double cost = 0;
		for (Car car : cars) {
			cost += car.getPrice();
		}
		return cost;
	}
	
	public TaxiStation sortByFuelConsumption() {
		Collections.sort(cars, new Comparator<Car>() {
	        public int compare(Car car1, Car car2) {
	            return (int) (car1.getAverageFuelConsumption() - car2.getAverageFuelConsumption());
	        }
	    });
		return this;
	}
	
	public TaxiStation sortByMaxSpeed() {
		Collections.sort(cars, new Comparator<Car>() {
	        public int compare(Car car1, Car car2) {
	            return (int) (car1.getMaxSpeed() - car2.getMaxSpeed());
	        }
	    });
		return this;
	}
	
	public List<Car> getCarsByMaxSpeedRange(double from, double to) {
	    List<Car> chosenCarsList = new ArrayList<>();
	    for (Car car : cars) {
	        if (car.getMaxSpeed() >= from && car.getMaxSpeed() <= to) {
	        	chosenCarsList.add(car);
	        }
	    }
	    return chosenCarsList;
	}
	
	public void print() {
	    for (Car car : cars) {
	    	System.out.println(car);
		}
	}
	
	public void print(List<? extends Car> cars) {
	    for (Car car : cars) {
	    	System.out.println(car);
		}
	}
}