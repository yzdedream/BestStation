package main.java.com.kelvin.beststation;

import java.util.HashMap;
import java.util.Map;

public abstract class CachedBestStationFinder extends BestStationFinder {
    Map<Point, BestStationResult> cache = new HashMap<>();

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
