import java.util.*;

class BusLocation {

    private String busNumber;
    private String currentLocation;

    public BusLocation(String busNumber, String currentLocation) {
        this.busNumber = busNumber;
        this.currentLocation = currentLocation;
    }

    public void displayLocation() {
        System.out.println("Bus Number: " + busNumber);
        System.out.println("Current Location: " + currentLocation);
    }
}

public class BusLocationTracker {

    public static void main(String[] args) {

        HashMap<String, BusLocation> buses = new HashMap<>();

        buses.put(
            "TS09Z1234",
            new BusLocation("TS09Z1234", "Ameerpet")
        );

        buses.put(
            "TS08Z5678",
            new BusLocation("TS08Z5678", "Kukatpally")
        );

        buses.put(
            "TS10Z9012",
            new BusLocation("TS10Z9012", "Secunderabad")
        );

        String searchBus = "TS08Z5678";

        System.out.println("Searching Bus: " + searchBus);

        if (buses.containsKey(searchBus)) {

            System.out.println("\nBus Found:");
            buses.get(searchBus).displayLocation();

        } else {

            System.out.println("Bus Not Found");
        }
    }
}
