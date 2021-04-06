package com.kelvin.beststation.test;

import com.kelvin.beststation.model.Point;
import com.kelvin.beststation.model.Station;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StationTest {

    @Test
    public void testIsInReach() {
        Station station = new Station(new Point(0, 0), 10);

        Point device = new Point(1, 1);
        assertTrue(station.isInReach(device));

        Point device2 = new Point(10, 10);
        assertFalse(station.isInReach(device2));

        Station station2 = new Station(new Point(0, 0), 0);
        Point device3 = new Point(0, 0);
        assertFalse(station2.isInReach(device3));
    }

    @Test(expected = NullPointerException.class)
    public void testStationWithNullPosition() {
        Station station = new Station(null, 0);
    }

    @Test
    public void testGetPower() {

    }
}
