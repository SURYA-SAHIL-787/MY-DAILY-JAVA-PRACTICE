import java.util.PriorityQueue;

class Flight implements Comparable<Flight> {

    String flightNumber;
    int priority;

    Flight(String flightNumber, int priority) {
        this.flightNumber = flightNumber;
        this.priority = priority;
    }

    @Override
    public int compareTo(Flight other) {
        return this.priority - other.priority;
    }
}

public class EmergencyLandingPriority {

    public static void main(String[] args) {

        PriorityQueue<Flight> queue = new PriorityQueue<>();

        queue.add(new Flight("AI101", 3));
        queue.add(new Flight("6E202", 2));
        queue.add(new Flight("EM999", 1));

        System.out.println("Landing Priority Order:");

        while (!queue.isEmpty()) {
            Flight flight = queue.poll();

            System.out.println(
                flight.flightNumber +
                " - Priority " +
                flight.priority
            );
        }
    }
}
