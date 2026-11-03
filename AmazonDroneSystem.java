import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

// -------------------------------------------------------------------
// 1. OOP: Abstract Base Class & Encapsulation (Telemetry & Asset)
// -------------------------------------------------------------------
abstract class AerialAsset {
    private final String droneId;
    protected double batteryPercent;

    public AerialAsset(String droneId, double batteryPercent) {
        this.droneId = droneId;
        this.batteryPercent = batteryPercent;
    }

    public String getDroneId() { return droneId; }
    public double getBatteryPercent() { return batteryPercent; }
    public abstract boolean canFulfillDistance(double distanceKm);
}

class PrimeAirDrone extends AerialAsset {
    private final double maxPayloadKg;

    public PrimeAirDrone(String droneId, double batteryPercent, double maxPayloadKg) {
        super(droneId, batteryPercent);
        this.maxPayloadKg = maxPayloadKg;
    }

    @Override
    public boolean canFulfillDistance(double distanceKm) {
        // Simple logic: 1% battery covers 0.5 km; must retain 15% safety buffer
        double usableBattery = getBatteryPercent() - 15.0;
        return usableBattery > 0 && (usableBattery * 0.5) >= distanceKm;
    }
}

// -------------------------------------------------------------------
// 2. DSA: Priority Queue & Graph Dijkstra Route Optimization
// -------------------------------------------------------------------
class DeliveryNode {
    String name;
    List<Edge> neighbors = new ArrayList<>();

    public DeliveryNode(String name) { this.name = name; }
    public void addPath(DeliveryNode target, double distanceKm) {
        neighbors.add(new Edge(target, distanceKm));
    }

    static class Edge {
        DeliveryNode target;
        double distanceKm;
        Edge(DeliveryNode target, double distanceKm) {
            this.target = target;
            this.distanceKm = distanceKm;
        }
    }
}

class RoutePlanner {
    // Dijkstra's Algorithm for Shortest Flight Path
    public static double findShortestPath(DeliveryNode start, DeliveryNode destination) {
        Map<DeliveryNode, Double> distances = new HashMap<>();
        PriorityQueue<DeliveryNode> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            DeliveryNode current = pq.poll();
            if (current == destination) return distances.get(destination);

            for (DeliveryNode.Edge edge : current.neighbors) {
                double newDist = distances.get(current) + edge.distanceKm;
                if (newDist < distances.getOrDefault(edge.target, Double.POSITIVE_INFINITY)) {
                    distances.put(edge.target, newDist);
                    pq.add(edge.target);
                }
            }
        }
        return -1.0; // Path not reachable
    }
}

// -------------------------------------------------------------------
// 3. Concurrency: Thread-Safe Dispatch Registry
// -------------------------------------------------------------------
class DispatchManager {
    private final Queue<PrimeAirDrone> availableDrones = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void registerDrone(PrimeAirDrone drone) {
        lock.lock();
        try {
            availableDrones.add(drone);
        } finally {
            lock.unlock();
        }
    }

    public PrimeAirDrone dispatchNext() {
        lock.lock();
        try {
            return availableDrones.poll();
        } finally {
            lock.unlock();
        }
    }
}

// -------------------------------------------------------------------
// Driver Code Execution
// -------------------------------------------------------------------
public class AmazonDroneSystem {
    public static void main(String[] args) {
        // Setup Nodes
        DeliveryNode hub = new DeliveryNode("Fulfillment Center BFI4");
        DeliveryNode wayPoint = new DeliveryNode("Waypoint-Alpha");
        DeliveryNode customerLoc = new DeliveryNode("Customer-Address");

        hub.addPath(wayPoint, 4.0);
        wayPoint.addPath(customerLoc, 3.5);

        // Find Shortest Path using Dijkstra
        double totalDistance = RoutePlanner.findShortestPath(hub, customerLoc);
        System.out.println("Calculated Delivery Route Distance: " + totalDistance + " km");

        // Dispatch Drone safely
        DispatchManager manager = new DispatchManager();
        PrimeAirDrone drone1 = new PrimeAirDrone("DRONE-MK30-01", 85.0, 2.5);
        manager.registerDrone(drone1);

        PrimeAirDrone activeDrone = manager.dispatchNext();
        if (activeDrone != null && activeDrone.canFulfillDistance(totalDistance)) {
            System.out.println("Dispatched Drone " + activeDrone.getDroneId() + " successfully!");
        } else {
            System.out.println("Drone battery insufficient for safe delivery.");
        }
    }
}
