package test.java;

import main.java.beststation.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testGetDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        double distance1 = p1.getDistance(p2);
        assertEquals(5, distance1, DELTA);
    }
}
