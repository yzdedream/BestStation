package com.kelvin.beststation.test;

import com.kelvin.beststation.DiligentCachedBestStationFinder;
import com.kelvin.beststation.model.BestStationResult;
import com.kelvin.beststation.model.Point;
import com.kelvin.beststation.model.Station;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DiligentCacheBestStationFinderTest {
    private static final double DELTA = 1e-15;
    private List<Point> devices;
    private DiligentCachedBestStationFinder diligent;


    @Before
    public void initDevices() {
        this.devices = new ArrayList<>();
        Point device1 = new Point(0, 0);
        Point device2 = new Point(100, 100);
        Point device3 = new Point(15, 10);
        Point device4 = new Point(18, 18);

        this.devices.add(device1);
        this.devices.add(device2);
        this.devices.add(device3);
        this.devices.add(device4);
    }

    @Before
    public void initStations() {
        this.diligent = new DiligentCachedBestStationFinder();
        Station station1 = new Station(new Point(0, 0), 10);
        this.diligent.addStation(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        this.diligent.addStation(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        this.diligent.addStation(station3);
    }

    @Test
    public void testGetBestStation() {
        Map<Point, BestStationResult> cache = this.diligent.getCache();
        int cacheSize = cache.size();
        int totalCoverageSize = 21 * 21 + 11 * 11 + 25 * 25 - 13 * 21;
        assertEquals(totalCoverageSize, cacheSize);

        Point device1 = this.devices.get(0);
        BestStationResult result = this.diligent.getBestStation(device1);

        cacheSize = cache.size();
        assertEquals(totalCoverageSize, cacheSize);

        assertTrue(result.isResultExist);
        assertEquals(new Point(0, 0), result.bestStation.position);
        assertEquals(10, result.bestStation.reach);
        assertEquals(100d, result.bestPower, DELTA);

        Point device2 = this.devices.get(1);
        BestStationResult result2 = this.diligent.getBestStation(device2);

        cacheSize = cache.size();
        assertEquals(totalCoverageSize + 1, cacheSize);

        assertFalse(result2.isResultExist);

        Point device3 = this.devices.get(2);
        BestStationResult result3 = this.diligent.getBestStation(device3);

        cacheSize = cache.size();
        assertEquals(totalCoverageSize + 1, cacheSize);

        assertTrue(result3.isResultExist);
        assertEquals(new Point(10, 0), result3.bestStation.position);
        assertEquals(12, result3.bestStation.reach);
        assertEquals(0.671842700025236d, result3.bestPower, DELTA);


        Point device4 = this.devices.get(3);
        BestStationResult result4 = this.diligent.getBestStation(device4);

        cacheSize = cache.size();
        assertEquals(totalCoverageSize + 1, cacheSize);

        assertTrue(result4.isResultExist);
        assertEquals(new Point(20, 20), result4.bestStation.position);
        assertEquals(5, result4.bestStation.reach);
        assertEquals(4.715728752538099, result4.bestPower, DELTA);

        this.diligent.getBestStation(device1);
        this.diligent.getBestStation(device2);
        this.diligent.getBestStation(device3);
        this.diligent.getBestStation(device4);
        cacheSize = cache.size();
        assertEquals(totalCoverageSize + 1, cacheSize);
    }

    @Test
    public void testDeleteStation() {
        Map<Point, BestStationResult> cache = this.diligent.getCache();

        int totalCoverageSize = 21 * 21 + 11 * 11 + 25 * 25 - 13 * 21;
        int cacheSize = cache.size();
        assertEquals(totalCoverageSize, cacheSize);

        Point device1 = this.devices.get(0);
        this.diligent.getBestStation(device1);

        cacheSize = cache.size();
        assertEquals(totalCoverageSize, cacheSize);

        Point pos = new Point(1, 1);
        Station station = new Station(pos, 10);
        this.diligent.deleteStation(station);
        cacheSize = cache.size();
        assertEquals(totalCoverageSize, cacheSize);

        Point pos2 = new Point(0, 0);
        Station station2 = new Station(pos2, 10);
        this.diligent.deleteStation(station2);
        cache = this.diligent.getCache();
        cacheSize = cache.size();
        assertEquals(totalCoverageSize, cacheSize);
    }
}
