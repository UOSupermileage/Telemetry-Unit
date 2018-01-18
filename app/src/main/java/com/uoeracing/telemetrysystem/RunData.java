package com.uoeracing.telemetrysystem;

import java.util.ArrayList;

/**
 * Run Data Object
 *
 * The run data class will store all positional data points for future viewing on the map. Each data point in the
 * list will be added to a marker on the map with data from that instance.
 */

public class RunData {
    ArrayList<PositionData> positions = new ArrayList<PositionData>();

    private String runName, startDate;

    public RunData(String id, String runName, String startDate) {
        this.runName = runName;
        this.startDate = startDate;
    }

    public ArrayList<PositionData> getPositions() {
        return positions;
    }

    public void addPosition(PositionData position) {
        this.positions.add(position);
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
