package test.java.com.kelvin.beststation;

import main.java.com.kelvin.beststation.BestStation;
import main.java.com.kelvin.beststation.BestStationResult;
import main.java.com.kelvin.beststation.Point;
import main.java.com.kelvin.beststation.Station;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BestStationTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testBestStation() {
        BestStation bestStation = new BestStation();
        List<Station> stations = new ArrayList<>();
        List<Point> devices = new ArrayList<>();

        Station station1 = new Station(new Point(0, 0), 10);
        stations.add(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        stations.add(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        stations.add(station3);

        bestStation.setStations(stations);
        Point device1 = new Point(0, 10);
        BestStationResult result = bestStation.getBestStation(device1);
        assertTrue(result.isResultExist);
        assertEquals(result.bestStation, station1);
        assertEquals(result.bestPower, 0, DELTA);

        Point device2 = new Point(30, 30);
        BestStationResult result2 = bestStation.getBestStation(device2);
        assertFalse(result2.isResultExist);
    }

    @Test(expected = NullPointerException.class)
    public void testBestStationWithNullStations() {
        BestStation bestStation = new BestStation();
        bestStation.setStations(null);
        Point device = new Point(0, 0);
        bestStation.getBestStation(device);
    }

    @Test(expected = NullPointerException.class)
    public void testBestStationWithNullDevice() {
        BestStation bestStation = new BestStation();
        bestStation.setStations(new ArrayList<>());
        bestStation.getBestStation(null);
    }
}
