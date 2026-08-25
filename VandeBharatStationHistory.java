import java.util.*;

class Station {

    private String stationName;

    public Station(String stationName) {
        this.stationName = stationName;
    }

    public void displayStation() {
        System.out.println(stationName);
    }
}

public class VandeBharatStationHistory {

    public static void main(String[] args) {

        Stack<Station> stations = new Stack<>();

        stations.push(new Station("Hyderabad"));
        stations.push(new Station("Warangal"));
        stations.push(new Station("Khammam"));
        stations.push(new Station("Vijayawada"));

        System.out.println("Stations Visited:");

        for (Station station : stations) {
            station.displayStation();
        }

        System.out.println("\nReverse Station Order:");

        while (!stations.isEmpty()) {
            stations.pop().displayStation();
        }
    }
}
