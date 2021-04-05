package main.java.com.kelvin.beststation;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        BestStation bestStation = new BestStation();
        this.runBestStation(bestStation);

        LazyCachedBestStation lazy = new LazyCachedBestStation();
        this.runBestStation(lazy);

        DiligentCachedBestStation diligent = new DiligentCachedBestStation();
        this.runBestStation(diligent);
    }

    private void runBestStation(BestStation bestStation) {
        this.setupStations(bestStation);
        List<Point> devices = this.initDevices();

        for (Point device : devices) {
            BestStationResult bestStationResult = bestStation.getBestStation(device);
            System.out.println(bestStationResult);
        }
        System.out.println("----------------");
    }

    private void setupStations(BestStation bestStation) {
        Station station1 = new Station(new Point(0, 0), 10);
        bestStation.addStation(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        bestStation.addStation(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        bestStation.addStation(station3);
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
