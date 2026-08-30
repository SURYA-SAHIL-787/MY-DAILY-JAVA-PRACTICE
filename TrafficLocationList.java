import java.util.ArrayList;

class TrafficLocation {

    String location;
    String trafficLevel;

    TrafficLocation(String location, String trafficLevel) {
        this.location = location;
        this.trafficLevel = trafficLevel;
    }
}

public class TrafficLocationList {

    public static void main(String[] args) {

        ArrayList<TrafficLocation> locations =
            new ArrayList<>();

        locations.add(
            new TrafficLocation("Silk Board", "High")
        );

        locations.add(
            new TrafficLocation("Electronic City", "Medium")
        );

        locations.add(
            new TrafficLocation("Hebbal", "High")
        );

        locations.add(
            new TrafficLocation("Whitefield", "Medium")
        );

        System.out.println("Bangalore Traffic Status:");

        for (TrafficLocation location : locations) {

            System.out.println(
                location.location +
                " - Traffic: " +
                location.trafficLevel
            );
        }
    }
}
