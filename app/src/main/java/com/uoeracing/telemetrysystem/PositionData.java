package com.uoeracing.telemetrysystem;

/**
 * Positional Data Point Object
 *
 * This class represents a single point on the map. There will be many of these per trip, each representing a data point on the map consisting of several data variables collected at the instant.
 */

public class PositionData {

    // Class variables to store instantaneous vehicular data.
    private int currentTime, currentLap, height;
    private double longitude, latitude, speed, altitude;
    private boolean isInclined;

    // Constructor defines all relevant data to be logged in object.
    public PositionData(int currentTime, int currentLap, int height, double longitude, double latitude, double speed, double altitude, boolean isInclined) {
        this.currentTime = currentTime;
        this.currentLap = currentLap;
        this.height = height;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.altitude = altitude;
        this.isInclined = isInclined;
    }


    // Getters and Setters
    public int getTime() {
        return currentTime;
    }

    public void setTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getLap() {
        return currentLap;
    }

    public void setLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public boolean isInclined() {
        return isInclined;
    }

    public void setInclined(boolean inclined) {
        isInclined = inclined;
    }
}
