package main.java.beststation;

import java.util.HashMap;
import java.util.Map;

public abstract class CachedBestStation extends BestStation {
    Map<Point, BestStationResult> cache = new HashMap<>();

    public abstract void addStation(Station station);

    public abstract void deleteStation(Station station);

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

    protected boolean isStationExist(Station target) {
        return this.getStations().contains(target);
    }

    public Map<Point, BestStationResult> getCache() {
        return cache;
    }
}
