import java.util.*;

class Reservation {

    private String seatNumber;
    private String passengerName;

    public Reservation(String seatNumber, String passengerName) {
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
    }

    public void displayReservation() {
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Passenger Name: " + passengerName);
    }
}

public class VandeBharatSeatReservation {

    public static void main(String[] args) {

        HashMap<String, Reservation> reservations = new HashMap<>();

        reservations.put(
            "C1-21",
            new Reservation("C1-21", "Sahil")
        );

        reservations.put(
            "C1-22",
            new Reservation("C1-22", "Rahul")
        );

        reservations.put(
            "C2-15",
            new Reservation("C2-15", "Arjun")
        );

        String searchSeat = "C1-22";

        System.out.println("Searching Seat: " + searchSeat);

        if (reservations.containsKey(searchSeat)) {

            System.out.println("\nReservation Found:");
            reservations.get(searchSeat).displayReservation();

        } else {

            System.out.println("Seat Not Reserved");
        }
    }
}
