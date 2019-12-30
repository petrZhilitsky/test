package com.epam.auto.test.level2.classes.ships;

import java.util.concurrent.atomic.AtomicInteger;

public class Berth {
    private int index;
    private AtomicInteger berthContainers = new AtomicInteger();
    private int maxBerthContainers;
    private Ship ship = null;

    public Berth(int index, int berthContainers, int maxBerthContainers) {
        this.index = index;
        this.berthContainers.set(berthContainers);
        this.maxBerthContainers = maxBerthContainers;
    }

    public boolean getContainer() {
        if (berthContainers.get() < maxBerthContainers) {
            berthContainers.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    public boolean giveContainer() {
        if (berthContainers.get() > 0) {
            berthContainers.decrementAndGet();
            return true;
        } else {
            return true;
        }
    }

    public int getBerthContainers() {
        return berthContainers.get();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getIndex() {
        return index;
    }
}
