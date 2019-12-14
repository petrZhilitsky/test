package com.epam.auto.test.level1.claasses.cars;

import com.epam.auto.test.level1.enums.EngineType;
import com.epam.auto.test.level1.enums.FuelType;

public class FuelCar extends Car {
	private FuelType fuelType;
	private double engineCapacity;
	
	public FuelCar(String make, String model, double price, double averageFuelConsumption,
			double maxSpeed, double maxCarryingWeight, int maxPassengerCapacity, FuelType fuelType,
			double engineCapacity) {
		super(make, model, price, EngineType.ICE, averageFuelConsumption, maxSpeed, maxCarryingWeight,
				maxPassengerCapacity);
		this.fuelType = fuelType;
		this.engineCapacity = engineCapacity;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public double getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(double engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + fuelType.toString().toLowerCase() + ";" + engineCapacity;
	}
}