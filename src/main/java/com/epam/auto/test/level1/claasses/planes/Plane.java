package com.epam.auto.test.level1.claasses.planes;

import java.util.Objects;

abstract public class Plane {
    private String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    public Plane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public Plane() {
		super();
	}

	public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
    
    public void setModel(String model) {
		this.model = model;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setMaxFlightDistance(int maxFlightDistance) {
		this.maxFlightDistance = maxFlightDistance;
	}

	public void setMaxLoadCapacity(int maxLoadCapacity) {
		this.maxLoadCapacity = maxLoadCapacity;
	}

	@Override
    public String toString() {
        return model + ";" + maxSpeed + ";" + maxFlightDistance + ";" + maxLoadCapacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Plane)) return false;
        Plane plane = (Plane) obj;
        return maxSpeed == plane.maxSpeed 
        		&& maxFlightDistance == plane.maxFlightDistance 
        		&& maxLoadCapacity == plane.maxLoadCapacity 
        		&& Objects.equals(model, plane.model);
    }
	
    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    }
}