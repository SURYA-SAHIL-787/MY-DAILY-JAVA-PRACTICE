import java.util.*;

class BusStop {
    private String stopName;

    public BusStop(String stopName) {
        this.stopName = stopName;
    }

    public void displayStop() {
        System.out.println(stopName);
    }
}

public class BusRouteHistory {

    public static void main(String[] args) {

        Stack<BusStop> routeHistory = new Stack<>();

        routeHistory.push(new BusStop("Miyapur"));
        routeHistory.push(new BusStop("Ameerpet"));
        routeHistory.push(new BusStop("Lakdikapul"));
        routeHistory.push(new BusStop("Mehdipatnam"));

        System.out.println("Visited Stops:");

        for (BusStop stop : routeHistory) {
            stop.displayStop();
        }

        System.out.println("\nReverse Route History:");

        while (!routeHistory.isEmpty()) {
            routeHistory.pop().displayStop();
        }
    }
}
