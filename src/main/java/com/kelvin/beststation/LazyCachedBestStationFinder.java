package main.java.com.kelvin.beststation;

import java.util.List;

public class LazyCachedBestStationFinder extends CachedBestStationFinder {
    private void initCache() {
        // do nothing in lazy mode
    }

    public void addStation(Station station) {
        List<Station> stations = this.getStations();
        if (!this.isStationExist(station)) {
            stations.add(station);
            this.cache.clear();
        }
    }

    public void deleteStation(Station station) {
        List<Station> stations = this.getStations();
        if (this.isStationExist(station)) {
            stations.remove(station);
            this.cache.clear();
        }
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
}