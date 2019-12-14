package com.epam.auto.test.level1.claasses.planes;

import java.util.Objects;

public class PassengerPlane extends Plane{

    private int passengersCapacity;

    public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, 
    		int maxLoadCapacity, int passengersCapacity) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.passengersCapacity = passengersCapacity;
    }
    
    public PassengerPlane() {
		super();
	}

	public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
		this.passengersCapacity = passengersCapacity;
	}

	@Override
    public String toString() {
        return super.toString() + ";" + passengersCapacity;
    }

    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
	
}