package com.epam.gomel.tat.framework.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("Cur_ID")
    private long id;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;
    @JsonProperty("Cur_Scale")
    private long scale;
    @JsonProperty("Cur_Name")
    private String name;
    @JsonProperty("Cur_OfficialRate")
    private double rate;

    public double getRate() {
        return rate;
    }
}
