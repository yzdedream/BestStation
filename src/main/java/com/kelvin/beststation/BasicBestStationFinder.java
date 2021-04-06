package main.java.com.kelvin.beststation;

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
