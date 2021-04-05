package main.java.beststation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LazyCachedBestStation extends CachedBestStation{
    private Map<Point, BestStationResult> cache = new HashMap<>();

    private void initCache() {
        // do nothing in lazy mode
    }

    public void addStation(Station station) {
        List<Station> stations = this.getStations();
        if (!isStationExist(station)) {
            stations.add(station);
            this.cache.clear();
        }
    }

    public void deleteStation(Station station) {
        List<Station> stations = this.getStations();
        if (isStationExist(station)) {
            stations.remove(station);
            this.cache.clear();
        }
    }

    private boolean isStationExist(Station target) {
        return this.getStations().contains(target);
    }

    @Override
    public BestStationResult getBestStation(Point device) {
        BestStationResult cacheResult = this.cache.get(device);
        if (cacheResult == null) {
            BestStationResult result = super.getBestStation(device);
            this.cache.put(device, result);
            cacheResult = result;
        }
        return cacheResult;
    }

    public Map<Point, BestStationResult> getCache() {
        return cache;
    }

}
