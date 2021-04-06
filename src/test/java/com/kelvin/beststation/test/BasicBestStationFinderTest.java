package com.kelvin.beststation.test;


import com.kelvin.beststation.BasicBestStationFinder;
import com.kelvin.beststation.BestStationFinder;
import com.kelvin.beststation.model.BestStationResult;
import com.kelvin.beststation.model.Point;
import com.kelvin.beststation.model.Station;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class BasicBestStationFinderTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testBasicBestStation() {
        BestStationFinder bestStationFinder = new BasicBestStationFinder();
        List<Station> stations = new ArrayList<>();
        List<Point> devices = new ArrayList<>();

        Station station1 = new Station(new Point(0, 0), 10);
        stations.add(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        stations.add(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        stations.add(station3);

        bestStationFinder.setStations(stations);
        Point device1 = new Point(0, 10);
        BestStationResult result = bestStationFinder.getBestStation(device1);
        assertTrue(result.isResultExist);
        assertEquals(result.bestStation, station1);
        assertEquals(result.bestPower, 0, DELTA);

        Point device2 = new Point(30, 30);
        BestStationResult result2 = bestStationFinder.getBestStation(device2);
        assertFalse(result2.isResultExist);
    }

    @Test(expected = NullPointerException.class)
    public void testBestStationWithNullStations() {
        BestStationFinder bestStationFinder = new BasicBestStationFinder();
        bestStationFinder.setStations(null);
        Point device = new Point(0, 0);
        bestStationFinder.getBestStation(device);
    }

    @Test(expected = NullPointerException.class)
    public void testBestStationWithNullDevice() {
        BestStationFinder bestStationFinder = new BasicBestStationFinder();
        bestStationFinder.setStations(new ArrayList<>());
        bestStationFinder.getBestStation(null);
    }
}
