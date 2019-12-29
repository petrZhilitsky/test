package com.epam.auto.test.level2;

import com.epam.auto.test.level2.classes.planes.Plane;
import com.epam.auto.test.level2.classes.planes.Runway;

import java.util.ArrayList;
import java.util.List;

public class Task_2_3_JavaThreads_optional {

    public static void main(String[] args) {
        int boardNumber = 0;
        List<Runway> runwayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Plane> planes = new ArrayList<Plane>();
            planes.add(new Plane(++boardNumber));
            planes.add(new Plane(++boardNumber));
            runwayList.add(new Runway(i + 1, planes));
        }
        for (Runway runway : runwayList) {
            runway.start();
        }
    }
}
