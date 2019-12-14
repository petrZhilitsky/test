package com.epam.auto.test;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.epam.auto.test.level1.claasses.airport.Airport;
import com.epam.auto.test.level1.claasses.models.ClassificationLevel;
import com.epam.auto.test.level1.claasses.models.ExperimentalTypes;
import com.epam.auto.test.level1.claasses.models.MilitaryType;
import com.epam.auto.test.level1.claasses.planes.ExperimentalPlane;
import com.epam.auto.test.level1.claasses.planes.MilitaryPlane;
import com.epam.auto.test.level1.claasses.planes.PassengerPlane;
import com.epam.auto.test.level1.claasses.planes.Plane;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
        new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
        new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
        new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
        new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
        new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
        new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
        new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
        new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
        new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
        new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
        new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
        new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
        new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
        new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
        new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
        new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanesList();
        boolean hasTransportPlanes = true;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() != MilitaryType.TRANSPORT)) {
            	hasTransportPlanes = false;
                break;
            }
        }
        Assert.assertTrue(hasTransportPlanes);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane planeWithMaxCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(planeWithMaxCapacity.equals(planeWithMaxPassengerCapacity));
    }

    @Test
    public void testSortPlanesByMaxLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }
    
    @Test
    public void testSortPlanesByMaxFlightDistance() {
        Airport airport = new Airport(planes);
        airport.sortByMaxFlightDistance();
        List<? extends Plane> planesSortedByMaxFlightDistance = airport.getPlanes();
        boolean nextPlaneMaxFlightDistanceIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxFlightDistance.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxFlightDistance.get(i);
            Plane nextPlane = planesSortedByMaxFlightDistance.get(i + 1);
            if (currentPlane.getMaxFlightDistance() > nextPlane.getMaxFlightDistance()) {
            	nextPlaneMaxFlightDistanceIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxFlightDistanceIsHigherThanCurrent);
    }
    
    @Test
    public void testSortPlanesByMaxSpeed() {
        Airport airport = new Airport(planes);
        airport.sortByMaxSpeed();
        List<? extends Plane> planesSortedByMaxSpeed = airport.getPlanes();
        boolean nextPlaneMaxSpeedIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxSpeed.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxSpeed.get(i);
            Plane nextPlane = planesSortedByMaxSpeed.get(i + 1);
            if (currentPlane.getMaxSpeed() > nextPlane.getMaxSpeed()) {
            	nextPlaneMaxSpeedIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxSpeedIsHigherThanCurrent);
    }

    @Test
    public void testGetBomberMilitaryPlanes() {
    	Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanesList();
        boolean hasBomberPlanes = true;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() != MilitaryType.BOMBER)) {
            	hasBomberPlanes = false;
                break;
            }
        }
        Assert.assertTrue(hasBomberPlanes);
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanesList();
        boolean hasPlaneWithClassificationLevelHigherThanUnclassified = false;
        int unclassifiedOrdinal = ClassificationLevel.UNCLASSIFIED.ordinal();
        for(ExperimentalPlane plane : experimentalPlanes){
            if(plane.getClassificationLevel().ordinal() > unclassifiedOrdinal){
            	hasPlaneWithClassificationLevelHigherThanUnclassified = true;
                break;
            }
        }
        Assert.assertTrue(hasPlaneWithClassificationLevelHigherThanUnclassified);
    }
}
