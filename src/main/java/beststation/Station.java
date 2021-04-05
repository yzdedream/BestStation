package main.java.beststation;

import java.util.Objects;

public class Station {
    public Point position;
    public int reach;

    public Station(Point position, int reach) {
        if (reach < 0) {
            reach = 0;
        }
        this.position = Objects.requireNonNull(position, "Station must have position");
        this.reach = reach;
    }

    public boolean isInReach(Point device) {
        double distance = this.position.getDistance(device);
        if (this.reach == 0) {
            return false;
        }
        return distance <= this.reach;
    }

    public double getPower(Point device) {
        if (this.isInReach(device)) {
            return Math.pow(this.reach - this.position.getDistance(device), 2);
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return reach == station.reach && position.equals(station.position);
    }
}
