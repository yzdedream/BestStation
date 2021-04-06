package com.kelvin.beststation.model;

import java.text.DecimalFormat;

public class BestStationResult {
    public boolean isResultExist = false;
    public Point device;
    public Station bestStation;
    public double bestPower;

    @Override
    public String toString() {
        DecimalFormat powerFormat = new DecimalFormat("#.0#");
        String emptyResult = "No link station within reach for point " + device.toString();
        if (this.isResultExist) {
            return "Best link station for point " + device.toString() + " is " + bestStation.position.toString() + " with power " + powerFormat.format(bestPower);
        } else {
            return emptyResult;
        }
    }
}
