import java.util.*;

class Passenger {
    private int passengerId;
    private String name;

    public Passenger(int passengerId, String name) {
        this.passengerId = passengerId;
        this.name = name;
    }

    public void displayPassenger() {
        System.out.println(
            "Passenger ID: " + passengerId +
            " | Name: " + name
        );
    }
}

public class BusPassengerQueue {

    public static void main(String[] args) {

        Queue<Passenger> queue = new LinkedList<>();

        queue.add(new Passenger(101, "Sahil"));
        queue.add(new Passenger(102, "Rahul"));
        queue.add(new Passenger(103, "Arjun"));
        queue.add(new Passenger(104, "Kiran"));

        System.out.println("Passengers Waiting:");

        for (Passenger passenger : queue) {
            passenger.displayPassenger();
        }

        System.out.println("\nBoarding Order:");

        while (!queue.isEmpty()) {
            queue.poll().displayPassenger();
        }
    }
}
