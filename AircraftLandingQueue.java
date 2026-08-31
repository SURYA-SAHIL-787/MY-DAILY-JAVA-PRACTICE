import java.util.LinkedList;
import java.util.Queue;

class Aircraft {
    String flightNumber;
    String airline;

    Aircraft(String flightNumber, String airline) {
        this.flightNumber = flightNumber;
        this.airline = airline;
    }
}

public class AircraftLandingQueue {

    public static void main(String[] args) {

        Queue<Aircraft> landingQueue = new LinkedList<>();

        landingQueue.add(new Aircraft("AI101", "Air India"));
        landingQueue.add(new Aircraft("6E202", "IndiGo"));
        landingQueue.add(new Aircraft("UK303", "Vistara"));

        System.out.println("Aircraft waiting to land:");

        for (Aircraft aircraft : landingQueue) {
            System.out.println(
                aircraft.flightNumber + " - " + aircraft.airline
            );
        }

        Aircraft landed = landingQueue.poll();

        System.out.println("\nLanding Permission Given To:");
        System.out.println(
            landed.flightNumber + " - " + landed.airline
        );
    }
}
