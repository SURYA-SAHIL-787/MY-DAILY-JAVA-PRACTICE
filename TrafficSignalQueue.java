import java.util.*;

public class TrafficSignalQueue {

    static HashMap<String, Queue<String>> traffic =
            new LinkedHashMap<>();

    static void addVehicle(String direction, String vehicle) {

        if (traffic.containsKey(direction)) {

            traffic.get(direction).offer(vehicle);

            System.out.println(
                    vehicle +
                    " joined the " +
                    direction +
                    " lane."
            );
        }
    }

    static void processTraffic() {

        System.out.println("\nVehicles passing through signal:");

        boolean vehiclesRemaining = true;

        while (vehiclesRemaining) {

            vehiclesRemaining = false;

            for (String direction : traffic.keySet()) {

                Queue<String> queue = traffic.get(direction);

                if (!queue.isEmpty()) {

                    vehiclesRemaining = true;

                    String vehicle = queue.poll();

                    System.out.println(
                            vehicle +
                            " from " +
                            direction +
                            " direction passed the signal."
                    );
                }
            }
        }
    }

    public static void main(String[] args) {

        traffic.put("North", new LinkedList<>());
        traffic.put("South", new LinkedList<>());
        traffic.put("East", new LinkedList<>());
        traffic.put("West", new LinkedList<>());

        addVehicle("North", "Car-N1");
        addVehicle("North", "Bike-N2");

        addVehicle("South", "Bus-S1");
        addVehicle("South", "Car-S2");

        addVehicle("East", "Auto-E1");
        addVehicle("East", "Car-E2");

        addVehicle("West", "Truck-W1");
        addVehicle("West", "Bike-W2");

        processTraffic();
    }
}
