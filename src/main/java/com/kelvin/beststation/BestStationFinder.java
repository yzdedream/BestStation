package com.kelvin.beststation;


import com.kelvin.beststation.model.BestStationResult;
import com.kelvin.beststation.model.Point;
import com.kelvin.beststation.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BestStationFinder {
    protected List<Station> stations = new ArrayList<>();

    public abstract void addStation(Station station);
    public abstract void deleteStation(Station station);

    public BestStationResult getBestStation(Point device) {
        Objects.requireNonNull(this.stations, "Station list must not be null");
        Objects.requireNonNull(device, "device must not be null");

        Station bestStation = null;
        double maxPower = 0;
        for (Station station : this.stations) {
            if (!station.isInReach(device)) {
                continue;
            }

            double power = station.getPower(device);
            if (bestStation == null) {
                bestStation = station;
                maxPower = power;
                continue;
            }

            if (power > maxPower) {
                bestStation = station;
                maxPower = power;
            }
        }

        return buildBestStationResult(device, bestStation, maxPower);
    }

    private BestStationResult buildBestStationResult(Point device, Station bestStation, double maxPower) {
        BestStationResult bestStationResult = new BestStationResult();
        bestStationResult.device = device;

        if (bestStation == null) {
            bestStationResult.isResultExist = false;
        } else {
            bestStationResult.isResultExist = true;
            bestStationResult.bestStation = bestStation;
            bestStationResult.bestPower = maxPower;
        }
        return bestStationResult;
    }

    protected boolean isStationExist(Station target) {
        return this.stations.contains(target);
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
