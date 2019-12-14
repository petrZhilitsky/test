package com.epam.auto.test.level1.claasses.cars;

import com.epam.auto.test.level1.enums.BatteryType;
import com.epam.auto.test.level1.enums.EngineType;

public class ElectricCar extends Car {
	private BatteryType batteryType;
	private double batteryCaopacity;
	
	public ElectricCar(String make, String model, double price, double averageFuelConsumption,
			double maxSpeed, double maxCarryingWeight, int maxPassengerCapacity, BatteryType batteryType,
			double batteryCaopacity) {
		super(make, model, price, EngineType.ELECTRIC, averageFuelConsumption, maxSpeed, maxCarryingWeight,
				maxPassengerCapacity);
		this.batteryType = batteryType;
		this.batteryCaopacity = batteryCaopacity;
	}

	public BatteryType getBatteryType() {
		return batteryType;
	}
	
	public void setBatteryType(BatteryType batteryType) {
		this.batteryType = batteryType;
	}
	
	public double getBatteryCaopacity() {
		return batteryCaopacity;
	}
	
	public void setBatteryCaopacity(double batteryCaopacity) {
		this.batteryCaopacity = batteryCaopacity;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + batteryType.toString().toLowerCase() + ";" + batteryCaopacity;
	}
	
}