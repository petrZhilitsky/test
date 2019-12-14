package com.epam.auto.test.level1;

import java.util.Arrays;
import java.util.List;

import com.epam.auto.test.level1.claasses.cars.TaxiStation;
import com.epam.auto.test.level1.claasses.cars.Car;
import com.epam.auto.test.level1.claasses.cars.ElectricCar;
import com.epam.auto.test.level1.claasses.cars.FuelCar;
import com.epam.auto.test.level1.enums.BatteryType;
import com.epam.auto.test.level1.enums.FuelType;

public class Task_04_Main_JavaColl {
	static List<Car> cars = Arrays.asList(
		new FuelCar("Ford", "Mustang", 25000, 7.2, 250, 300, 5, FuelType.GASOLINE, 3.7),
		new FuelCar("Audi", "Q7", 15000, 5.6, 140, 400, 5, FuelType.GASOLINE, 2.2),
		new FuelCar("VW", "Passat", 12000, 6.1, 120, 450, 5, FuelType.DIESEL, 1.9),
		new ElectricCar("Ford", "Focus", 35000, 1.5, 135, 200, 5, BatteryType.LITHIUM_ION, 50),
		new ElectricCar("Tesla", "Model S", 70000, 1.8, 150, 250, 5, BatteryType.NICKEL_METAL_HYDRIDE, 60),
		new ElectricCar("Porsche", "Panamera", 90000, 1.8, 200, 200, 2, BatteryType.LEAD_ACID, 65)
	);

	public static void main(String[] args) {
		TaxiStation taxiStation = new TaxiStation(cars);
		System.out.println("Cost of all cars: " + taxiStation.totalCost());
		System.out.println("Cars sorted by consumption:");
		taxiStation.sortByFuelConsumption().print();
		System.out.println("Cars with max speed 200 - 250:");
		taxiStation.print(taxiStation.getCarsByMaxSpeedRange(200, 250));
	}
}