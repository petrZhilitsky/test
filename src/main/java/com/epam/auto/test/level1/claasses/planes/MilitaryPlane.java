package com.epam.auto.test.level1.claasses.planes;

import java.util.Objects;

import com.epam.auto.test.level1.claasses.models.MilitaryType;

public class MilitaryPlane extends Plane{

    private MilitaryType militaryType;

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, 
    		int maxLoadCapacity, MilitaryType militaryType) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.militaryType = militaryType;
    }

	public MilitaryPlane() {
		super();
	}

	public MilitaryType getMilitaryType() {
		return militaryType;
	}

	public void setMilitaryType(MilitaryType militaryType) {
		this.militaryType = militaryType;
	}

	@Override
    public String toString() {
        return super.toString() + ";" + militaryType;
    }

    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), militaryType);
    }
}
