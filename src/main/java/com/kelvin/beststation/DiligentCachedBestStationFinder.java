package main.java.com.kelvin.beststation;

import main.java.com.kelvin.beststation.model.Point;
import main.java.com.kelvin.beststation.model.Station;

import java.util.ArrayList;
import java.util.List;

public class DiligentCachedBestStationFinder extends CachedBestStationFinder {

    @Override
    public void addStation(Station station) {
        List<Station> stations = this.getStations();
        if (!this.isStationExist(station)) {
            stations.add(station);
            this.updateCacheOnStationChange(station);
        }
    }

    private void updateCacheOnStationChange(Station station) {
        List<Point> coverage = this.getCoverage(station);
        this.updateCacheByCoverage(coverage);
    }

    private void updateCacheByCoverage(List<Point> coverage) {
        for (Point point : coverage) {
            this.cache.remove(point);
            this.cache.put(point, super.getBestStation(point));
        }

    }

    @Override
    public void deleteStation(Station station) {
        List<Station> stations = this.getStations();
        if (this.isStationExist(station)) {
            stations.remove(station);
            this.updateCacheOnStationChange(station);
        }
    }

    public List<Point> getCoverage(Station station) {
        Point center = station.position;
        Point topLeft = new Point(center.x - station.reach, center.y + station.reach);
        Point bottomRight = new Point(center.x + station.reach, center.y - station.reach);
        return this.generateMatrix(topLeft, bottomRight);
    }

    private List<Point> generateMatrix(Point topLeft, Point bottomRight) {
        List<Point> matrix = new ArrayList<>();
        for (int x = topLeft.x; x <= bottomRight.x; x++) {
            for (int y = topLeft.y; y >= bottomRight.y; y--) {
                matrix.add(new Point(x, y));
            }
        }
        return matrix;
    }
}
