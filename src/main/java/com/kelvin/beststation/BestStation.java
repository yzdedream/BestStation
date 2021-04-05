package main.java.com.kelvin.beststation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BestStation {
    private List<Station> stations = new ArrayList<>();

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

    public void addStation(Station station) {
        if (!this.isStationExist(station)) {
            this.stations.add(station);
        }
    }

    public void deleteStation(Station station) {
        if (this.isStationExist(station)) {
            this.stations.remove(station);
        }
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
