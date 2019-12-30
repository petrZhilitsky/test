package com.epam.auto.test.level2.classes.ships;

import java.util.List;

public class RiverPort {
    private String name;
    private List<Berth> berthList;

    public RiverPort(String name, List<Berth> berthList) {
        this.name = name;
        this.berthList = berthList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Berth> getBerthList() {
        return berthList;
    }
}