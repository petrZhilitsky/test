package com.epam.auto.test.level1.claasses.airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.auto.test.level1.claasses.models.MilitaryType;
import com.epam.auto.test.level1.claasses.planes.ExperimentalPlane;
import com.epam.auto.test.level1.claasses.planes.MilitaryPlane;
import com.epam.auto.test.level1.claasses.planes.PassengerPlane;
import com.epam.auto.test.level1.claasses.planes.Plane;

public class Airport {
	private List<? extends Plane> planes;
	
	public Airport() {
		super();
	}

	public Airport(List<? extends Plane> planes) {
		this.planes = planes;
	}

	public List<? extends Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<? extends Plane> planes) {
		this.planes = planes;
	}

	public List<PassengerPlane> getPassengerPlanesList() {
	    List<PassengerPlane> passengerPlanesList = new ArrayList<>();
	    for (Plane plane : planes) {
	    	if (plane instanceof PassengerPlane) {
	    		passengerPlanesList.add((PassengerPlane) plane);
	    	}
	    }
	    return passengerPlanesList;
	}

	public List<MilitaryPlane> getMilitaryPlanesList() {
		List<MilitaryPlane> militaryPlanesList = new ArrayList<>();
		for (Plane plane : planes) {
        	if (plane instanceof MilitaryPlane) {
        		militaryPlanesList.add((MilitaryPlane) plane);
        	}
		}
		return militaryPlanesList;
	}

	public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
		List<PassengerPlane> passengerPlanesList = getPassengerPlanesList();
		PassengerPlane planeWithMaxCapacity = passengerPlanesList.get(0);
		for (int i = 0; i < passengerPlanesList.size(); i++) {
        	if (passengerPlanesList.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
        		planeWithMaxCapacity = passengerPlanesList.get(i);
        	}
		}
		return planeWithMaxCapacity;
	}

	public List<MilitaryPlane> getTransportMilitaryPlanesList() {
		List<MilitaryPlane> transportMilitaryPlanesList = new ArrayList<>();
		List<MilitaryPlane> militaryPlanesList = getMilitaryPlanesList();
		for (MilitaryPlane plane : militaryPlanesList) {
			if (plane.getMilitaryType() == MilitaryType.TRANSPORT) {
				transportMilitaryPlanesList.add(plane);
			}
		}
		return transportMilitaryPlanesList;
	}

	public List<MilitaryPlane> getBomberMilitaryPlanesList() {
		List<MilitaryPlane> bomberMilitaryPlanesList = new ArrayList<>();
		List<MilitaryPlane> militaryPlanes = getMilitaryPlanesList();
		for (MilitaryPlane plane : militaryPlanes) {
			if (plane.getMilitaryType() == MilitaryType.BOMBER) {
				bomberMilitaryPlanesList.add(plane);
			}
		}
		return bomberMilitaryPlanesList;
	}

	public List<ExperimentalPlane> getExperimentalPlanesList() {
	    List<ExperimentalPlane> experimentalPlanesList = new ArrayList<>();
	    for (Plane plane : planes) {
	        if (plane instanceof ExperimentalPlane) {
	            experimentalPlanesList.add((ExperimentalPlane) plane);
	        }
	    }
	    return experimentalPlanesList;
	}

	public Airport sortByMaxFlightDistance() {
	    Collections.sort(planes, new Comparator<Plane>() {
	        public int compare(Plane plane1, Plane plane2) {
	            return plane1.getMaxFlightDistance() - plane2.getMaxFlightDistance();
	        }
	    });
	    return this;
	}

	public Airport sortByMaxSpeed() {
	    Collections.sort(planes, new Comparator<Plane>() {
	        public int compare(Plane plane1, Plane plane2) {
	            return plane1.getMaxSpeed() - plane2.getMaxSpeed();
	        }
	    });
	    return this;
	}

	public Airport sortByMaxLoadCapacity() {
	    Collections.sort(planes, new Comparator<Plane>() {
	        public int compare(Plane plane1, Plane plane2) {
	            return plane1.getMaxLoadCapacity() - plane2.getMaxLoadCapacity();
	        }
	    });
	    return this;
	}
	
	public void print() {
	    for (Plane plane : planes) {
	    	System.out.println(plane);
		}
	}
	
	public static void print(List<? extends Plane> planes) {
		for (Plane plane : planes) {
	    	System.out.println(plane);
		}
	}
}