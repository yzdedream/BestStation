package main.java.com.kelvin.beststation;

import main.java.com.kelvin.beststation.model.BestStationResult;
import main.java.com.kelvin.beststation.model.Point;
import main.java.com.kelvin.beststation.model.Station;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        System.out.println("Running basic best station finder");
        BestStationFinder bestStationFinder = new BasicBestStationFinder();
        this.runBestStation(bestStationFinder);

        System.out.println("Running lazy cached best station finder");
        LazyCachedBestStationFinder lazy = new LazyCachedBestStationFinder();
        this.runBestStation(lazy);

        System.out.println("Running diligent cached best station finder");
        DiligentCachedBestStationFinder diligent = new DiligentCachedBestStationFinder();
        this.runBestStation(diligent);
    }

    private void runBestStation(BestStationFinder bestStationFinder) {
        this.initStations(bestStationFinder);
        List<Point> devices = this.initDevices();

        for (Point device : devices) {
            BestStationResult bestStationResult = bestStationFinder.getBestStation(device);
            System.out.println(bestStationResult);
        }
        System.out.println("----------------");
    }

    private void initStations(BestStationFinder bestStationFinder) {
        Station station1 = new Station(new Point(0, 0), 10);
        bestStationFinder.addStation(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        bestStationFinder.addStation(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        bestStationFinder.addStation(station3);
    }

    private List<Point> initDevices() {
        List<Point> devices = new ArrayList<>();
        Point device1 = new Point(0, 0);
        Point device2 = new Point(100, 100);
        Point device3 = new Point(15, 10);
        Point device4 = new Point(18, 18);

        devices.add(device1);
        devices.add(device2);
        devices.add(device3);
        devices.add(device4);

        return devices;
    }

}
