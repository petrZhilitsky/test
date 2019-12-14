package com.epam.auto.test.level1.claasses;

import java.time.Year;
import java.util.Random;

public class Car {
	private String id;
	private String name;
	private String model;
	private int year;
	private String color;
	private double cost;
	private String regNum;
	
	public Car(String name, String model, int year, String color, double cost, String regNum) {
		setId();
		this.name = name;
		this.model = model;
		this.year = year;
		this.color = color;
		this.cost = cost;
		this.regNum = regNum;
	}
	
	public Car(String name, String model, String color, double cost) {
		setId();
		this.name = name;
		this.model = model;
		this.year = Year.now().getValue();
		this.color = color;
		this.cost = 0.00;
		this.regNum = null;
	}

	public Car() {
		super();
	}

	public String getId() {
		return id;
	}

	private void setId() {
		Random random = new Random();
		String values = "qwertyuiopasdfghjklzxcvbnm1234567890";
		String id = "";
		for (int i = 0; i < 8; i++) {
			id += values.charAt(random.nextInt(values.length()));
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	@Override
	public String toString() {
		return id + ";" + name + ";" + model + ";" + year + ";" + color + ";" + cost + ";" + regNum;
	}
}