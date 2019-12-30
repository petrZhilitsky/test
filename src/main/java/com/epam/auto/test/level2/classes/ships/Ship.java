package com.epam.auto.test.level2.classes.ships;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Ship implements Runnable {
    private int index;
    private int shipContainers;
    private int maxShipContainers;
    private final List<Berth> BERTHLIST;
    private Berth berth;
    private boolean loadingOperations;
    private boolean unloadingOperations;
    private Semaphore semaphore;

    public Ship(int index, int shipContainers, int maxShipContainers, List<Berth> berthList,
                boolean loadingOperations, boolean unloadingOperations, Semaphore semaphore) {
        this.index = index;
        this.shipContainers = shipContainers;
        this.maxShipContainers = maxShipContainers;
        this.BERTHLIST = berthList;
        this.loadingOperations = loadingOperations;
        this.unloadingOperations = unloadingOperations;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println("Ship " + index + " arrived to port");

        try {
            semaphore.acquire();
            System.out.println("    Ship " + index + " waiting for free berth");

            //check for free berth
            int berthIndex = 0;
            synchronized (BERTHLIST) {
                for (Berth berth : BERTHLIST) {
                    if (berth.getShip() == null) {
                        berthIndex = berth.getIndex();
                        berth.setShip(this);
                        this.berth = berth;
                        System.out.println("        Ship " + index + " (" + shipContainers + " cont.) landed on berth "
                                + this.berth.getIndex() + " (" + this.berth.getBerthContainers() + " cont.)");
                        break;
                    }
                }
            }

            // start loading/unloading operations
            Random random = new Random();
            int unloadContainers = random.nextInt(shipContainers + 1);
            boolean isUnloaded = false;
            for (int i = 0; i < unloadContainers; i++) {
                if (unloadingOperations && shipContainers > 0 && berth.getContainer()) {
                    shipContainers--;
                    isUnloaded = true;
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
            if (isUnloaded) {
                System.out.println("        Ship " + index + " unloaded " + unloadContainers + " containers");
            } else if (unloadingOperations) {
                System.out.println("        Ship " + index + " unloading failed");
            }

            int loadContainers = random.nextInt(berth.getBerthContainers() + 1);
            boolean isLoaded = false;
            for (int i = 0; i < loadContainers; i++) {
                if (loadingOperations && shipContainers < maxShipContainers && berth.giveContainer()) {
                    shipContainers++;
                    isLoaded = true;
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
            if (isLoaded) {
                System.out.println("        Ship " + index + " loaded " + loadContainers + " containers");
            } else if (loadingOperations) {
                System.out.println("        Ship " + index + " loading failed");
            }

            // release of occupied berth
            synchronized (BERTHLIST) {
                for (Berth berth : BERTHLIST) {
                    if (berthIndex == berth.getIndex()) {
                        berth.setShip(null);
                        System.out.println("    Ship " + index + " (" + shipContainers + " cont.) left berth "
                                + this.berth.getIndex() + " (" + this.berth.getBerthContainers() + " cont.)");
                        this.berth = null;
                        break;
                    }
                }
            }
            semaphore.release();

            System.out.println("Ship " + index + " departured from port");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
