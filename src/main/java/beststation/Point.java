package main.java.beststation;

import java.text.DecimalFormat;

public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point point2) {
        return Math.sqrt(Math.pow(point2.x - this.x, 2)
                + Math.pow(point2.y - this.y, 2));
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return "" + decimalFormat.format(x) + "," + decimalFormat.format(y);
    }
}
