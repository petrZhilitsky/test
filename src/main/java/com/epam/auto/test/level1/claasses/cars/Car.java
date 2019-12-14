package com.epam.auto.test.level1.claasses.cars;

import com.epam.auto.test.level1.enums.EngineType;

public class Car {
	private String make;
	private String model;
	private double price;
	private EngineType engineType;
	private double averageFuelConsumption;
	private double maxSpeed;
	private double maxCarryingWeight;
	private int maxPassengerCapacity;
	
	public Car(String make, String model, double price, EngineType engineType, double averageFuelConsumption,
			double maxSpeed, double maxCarryingWeight, int maxPassengerCapacity) {
		super();
		this.make = make;
		this.model = model;
		this.price = price;
		this.engineType = engineType;
		this.averageFuelConsumption = averageFuelConsumption;
		this.maxSpeed = maxSpeed;
		this.maxCarryingWeight = maxCarryingWeight;
		this.maxPassengerCapacity = maxPassengerCapacity;
	}

	public Car() {
		super();
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getEngineType() {
		return engineType.toString().toLowerCase();
	}

	public double getAverageFuelConsumption() {
		return averageFuelConsumption;
	}

	public void setAverageFuelConsumption(double averageFuelConsumption) {
		this.averageFuelConsumption = averageFuelConsumption;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getMaxCarryingWeight() {
		return maxCarryingWeight;
	}

	public void setMaxCarryingWeight(double maxCarryingWeight) {
		this.maxCarryingWeight = maxCarryingWeight;
	}

	public int getMaxPassengerCapacity() {
		return maxPassengerCapacity;
	}

	public void setMaxPassengerCapacity(int maxPassengerCapacity) {
		this.maxPassengerCapacity = maxPassengerCapacity;
	}

	@Override
	public String toString() {
		return make + ";" + model + ";" + price + ";" + maxSpeed + ";" + maxCarryingWeight + ";"
				+ maxPassengerCapacity + ";" + engineType.toString().toLowerCase() + ";"
				+ averageFuelConsumption;
	}
}