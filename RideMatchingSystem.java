import java.util.*;

class Driver {
    String id;
    String name;
    double distanceKm;

    public Driver(String id, String name, double distanceKm) {
        this.id = id;
        this.name = name;
        this.distanceKm = distanceKm;
    }
}

public class RideMatchingSystem {
    private PriorityQueue<Driver> driverQueue;

    public RideMatchingSystem() {
        // Min-Heap based on distance
        this.driverQueue = new PriorityQueue<>(Comparator.comparingDouble(d -> d.distanceKm));
    }

    public void addAvailableDriver(Driver driver) {
        driverQueue.offer(driver);
    }

    public Driver findNearestDriver() {
        return driverQueue.poll();
    }

    public static void main(String[] args) {
        RideMatchingSystem system = new RideMatchingSystem();
        system.addAvailableDriver(new Driver("D1", "Ramesh", 3.5));
        system.addAvailableDriver(new Driver("D2", "Suresh", 1.2));
        system.addAvailableDriver(new Driver("D3", "Amit", 4.0));

        Driver assigned = system.findNearestDriver();
        System.out.println("Assigned Driver: " + assigned.name + " (" + assigned.distanceKm + " km away)");
    }
}
