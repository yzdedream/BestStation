package test.java.com.kelvin.beststation;

import main.java.com.kelvin.beststation.model.BestStationResult;
import main.java.com.kelvin.beststation.LazyCachedBestStationFinder;
import main.java.com.kelvin.beststation.model.Point;
import main.java.com.kelvin.beststation.model.Station;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LazyCachedBestStationFinderTest {
    private static final double DELTA = 1e-15;
    private List<Point> devices;
    private LazyCachedBestStationFinder lazy;


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
        this.lazy = new LazyCachedBestStationFinder();
        Station station1 = new Station(new Point(0, 0), 10);
        this.lazy.addStation(station1);

        Station station2 = new Station(new Point(20, 20), 5);
        this.lazy.addStation(station2);

        Station station3 = new Station(new Point(10, 0), 12);
        this.lazy.addStation(station3);
    }

    @Test
    public void testGetBestStation() {
        Map<Point, BestStationResult> cache = this.lazy.getCache();

        int cacheSize = cache.size();
        assertEquals(0, cacheSize);

        Point device1 = this.devices.get(0);
        BestStationResult result = this.lazy.getBestStation(device1);

        cacheSize = cache.size();
        assertEquals(1, cacheSize);

        assertTrue(result.isResultExist);
        assertEquals(new Point(0, 0), result.bestStation.position);
        assertEquals(10, result.bestStation.reach);
        assertEquals(100d, result.bestPower, DELTA);

        Point device2 = this.devices.get(1);
        BestStationResult result2 = this.lazy.getBestStation(device2);

        cacheSize = cache.size();
        assertEquals(2, cacheSize);

        assertFalse(result2.isResultExist);

        Point device3 = this.devices.get(2);
        BestStationResult result3 = this.lazy.getBestStation(device3);

        cacheSize = cache.size();
        assertEquals(3, cacheSize);

        assertTrue(result3.isResultExist);
        assertEquals(new Point(10, 0), result3.bestStation.position);
        assertEquals(12, result3.bestStation.reach);
        assertEquals(0.671842700025236d, result3.bestPower, DELTA);


        Point device4 = this.devices.get(3);
        BestStationResult result4 = this.lazy.getBestStation(device4);

        cacheSize = cache.size();
        assertEquals(4, cacheSize);

        assertTrue(result4.isResultExist);
        assertEquals(new Point(20, 20), result4.bestStation.position);
        assertEquals(5, result4.bestStation.reach);
        assertEquals(4.715728752538099, result4.bestPower, DELTA);

        this.lazy.getBestStation(device1);
        this.lazy.getBestStation(device2);
        this.lazy.getBestStation(device3);
        this.lazy.getBestStation(device4);
        cacheSize = cache.size();
        assertEquals(4, cacheSize);
    }

    @Test
    public void testDeleteStation() {
        Map<Point, BestStationResult> cache = this.lazy.getCache();
        int cacheSize = cache.size();
        assertEquals(0, cacheSize);

        Point device1 = this.devices.get(0);
        this.lazy.getBestStation(device1);

        cacheSize = cache.size();
        assertEquals(1, cacheSize);

        Point pos = new Point(1, 1);
        Station station = new Station(pos, 10);
        this.lazy.deleteStation(station);
        cacheSize = cache.size();
        assertEquals(1, cacheSize);

        Point pos2 = new Point(0, 0);
        Station station2 = new Station(pos2, 10);
        this.lazy.deleteStation(station2);
        cache = this.lazy.getCache();
        cacheSize = cache.size();
        assertEquals(0, cacheSize);
    }
}
