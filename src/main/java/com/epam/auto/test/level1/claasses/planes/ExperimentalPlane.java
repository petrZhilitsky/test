package com.epam.auto.test.level1.claasses.planes;

import java.util.Objects;

import com.epam.auto.test.level1.claasses.models.ClassificationLevel;
import com.epam.auto.test.level1.claasses.models.ExperimentalTypes;

public class ExperimentalPlane extends Plane{

    private ExperimentalTypes experimentalType;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, 
    		ExperimentalTypes experimentalType, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }
    
    public ExperimentalPlane() {
		super();
	}

	public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel){
        this.classificationLevel = classificationLevel;
    }
    
    public ExperimentalTypes getExperimentalType() {
		return experimentalType;
	}

	public void setExperimentalType(ExperimentalTypes experimentalType) {
		this.experimentalType = experimentalType;
	}

	@Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

    @Override
    public String toString() {
    	return super.toString() + ";" + experimentalType + ";" 
        		+ classificationLevel;
    }
}