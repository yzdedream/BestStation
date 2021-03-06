package com.kelvin.beststation;


import com.kelvin.beststation.model.Station;

public class BasicBestStationFinder extends BestStationFinder{
    public void addStation(Station station) {
        if (!this.isStationExist(station)) {
            this.stations.add(station);
        }
    }

    public void deleteStation(Station station) {
        if (this.isStationExist(station)) {
            this.stations.remove(station);
        }
    }
}
