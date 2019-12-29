package com.epam.auto.test.level2;

import com.epam.auto.test.level2.classes.ships.Berth;
import com.epam.auto.test.level2.classes.ships.RiverPort;
import com.epam.auto.test.level2.classes.ships.Ship;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task_2_3_JavaThreads {

    public static void main(String[] args) {
        RiverPort port = new RiverPort("River port Gomel", Arrays.asList(
                new Berth(1, 10, 20),
                new Berth(2, 25, 30),
                new Berth(3, 15, 35),
                new Berth(4, 0, 30),
                new Berth(5, 20, 20)
        ));

        final Semaphore SEMAPHORE = new Semaphore(port.getBerthList().size(), true);

        List<Ship> shipList = Arrays.asList(
                new Ship(1, 20, 25, port.getBerthList(), true, false, SEMAPHORE),
                new Ship(2, 5, 5, port.getBerthList(), false, true, SEMAPHORE),
                new Ship(3, 15, 30, port.getBerthList(), false, true, SEMAPHORE),
                new Ship(4, 0, 40, port.getBerthList(), true, false, SEMAPHORE),
                new Ship(5, 25, 25, port.getBerthList(), false, true, SEMAPHORE),
                new Ship(6, 10, 30, port.getBerthList(), true, true, SEMAPHORE),
                new Ship(7, 0, 35, port.getBerthList(), true, false, SEMAPHORE),
                new Ship(8, 20, 20, port.getBerthList(), false, true, SEMAPHORE)
        );

        for (Ship ship : shipList) {
            new Thread(ship).start();
        }
    }
}
