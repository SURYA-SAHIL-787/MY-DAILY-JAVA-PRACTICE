import java.util.*;

class Passenger {
    private int passengerId;
    private String name;
    private String coach;

    public Passenger(int passengerId, String name, String coach) {
        this.passengerId = passengerId;
        this.name = name;
        this.coach = coach;
    }

    public void displayPassenger() {
        System.out.println(
            "ID: " + passengerId +
            " | Name: " + name +
            " | Coach: " + coach
        );
    }
}

public class VandeBharatPassengerQueue {

    public static void main(String[] args) {

        Queue<Passenger> queue = new LinkedList<>();

        queue.add(new Passenger(101, "Sahil", "C1"));
        queue.add(new Passenger(102, "Rahul", "C2"));
        queue.add(new Passenger(103, "Arjun", "C3"));
        queue.add(new Passenger(104, "Kiran", "C1"));

        System.out.println("Passengers Waiting for Boarding:");

        for (Passenger passenger : queue) {
            passenger.displayPassenger();
        }

        System.out.println("\nBoarding Order:");

        while (!queue.isEmpty()) {
            queue.poll().displayPassenger();
        }
    }
}
