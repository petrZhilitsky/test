package com.epam.auto.test.level2.classes.planes;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Runway extends Thread {
    private int number;
    private List<Plane> planes;

    public Runway(int number, List<Plane> planes) {
        this.planes = planes;
        this.number = number;
    }

    @Override
    public void run() {
        for (Plane plane : planes) {
            System.out.println(" The runway #" + number + " \"took\" the plane #" + plane.getBoardNumber());
            try {
                System.out.println(" The plane #" + plane.getBoardNumber() + " began to enter the runway #" + number);
                TimeUnit.SECONDS.sleep(3);
                System.out.println(" The plane #" + plane.getBoardNumber() + " took off");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" The runway #" + number + " is free");
        }
    }
}
