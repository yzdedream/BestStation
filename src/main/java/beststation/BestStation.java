package main.java.beststation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BestStation {

    private List<Station> stations = new ArrayList<>();
    private List<Point> devices = new ArrayList<>();


    public static void main(String[] args) {
        BestStation bestStation = new BestStation();
        bestStation.run();
    }

    private void run() {
        this.setupStations();
        this.setupDevices();

        for (Point device : this.devices) {
            BestStationResult bestStationResult = this.getBestStation(device);
            System.out.println(bestStationResult);
        }
    }

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

    private void setupStations() {
        Station station1 = new Station(new Point(0, 0), 10);
        this.stations.add(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        this.stations.add(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        this.stations.add(station3);
    }

    private void setupDevices() {
        Point device1 = new Point(0, 0);
        Point device2 = new Point(100, 100);
        Point device3 = new Point(15, 10);
        Point device4 = new Point(18, 18);

        this.devices.add(device1);
        this.devices.add(device2);
        this.devices.add(device3);
        this.devices.add(device4);
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Point> getDevices() {
        return devices;
    }

    public void setDevices(List<Point> devices) {
        this.devices = devices;
    }
}
