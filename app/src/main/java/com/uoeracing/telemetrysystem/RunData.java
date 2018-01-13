package com.uoeracing.telemetrysystem;

import java.util.ArrayList;

/**
 * Run Data Object
 *
 * The run data class will store all positional data points for future viewing on the map. Each data point in the
 * list will be added to a marker on the map with data from that instance.
 */

public class RunData {
    private String key;
    private String totalTime;
    private double totalLaps;

    public RunData() {

    }
    public RunData(String id, String productname, double price) {
        this.key = key;
        this.totalTime = totalTime;
        this.totalLaps = totalLaps;
    }
    public RunData(String productname, double price) {
        this.totalTime = totalTime;
        this.totalLaps = totalLaps;
    }

    public double getTotalLaps() {
        return totalLaps;
    }

    public String getKey() {
        return key;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTotalLaps(double totalLaps) {
        this.totalLaps = totalLaps;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

}
