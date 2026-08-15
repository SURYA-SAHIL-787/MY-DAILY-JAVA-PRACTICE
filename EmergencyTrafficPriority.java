import java.util.PriorityQueue;

class Vehicle implements Comparable<Vehicle> {
    String vehicleNumber;
    String type;
    int priority;

    Vehicle(String vehicleNumber, String type, int priority) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.priority = priority;
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.priority - other.priority;
    }
}

public class EmergencyTrafficPriority {

    public static void main(String[] args) {

        PriorityQueue<Vehicle> trafficQueue = new PriorityQueue<>();

        trafficQueue.add(new Vehicle("KA01AB1234", "Car", 3));
        trafficQueue.add(new Vehicle("KA02EM1001", "Ambulance", 1));
        trafficQueue.add(new Vehicle("KA03CD5678", "Bus", 2));
        trafficQueue.add(new Vehicle("KA04FR9999", "Fire Truck", 1));
        trafficQueue.add(new Vehicle("KA05XY4321", "Bike", 3));

        System.out.println("Traffic Signal Vehicle Processing Order:");

        while (!trafficQueue.isEmpty()) {

            Vehicle v = trafficQueue.poll();

            System.out.println(
                    v.vehicleNumber +
                    " - " +
                    v.type +
                    " - Priority: " +
                    v.priority
            );
        }
    }
}
