import java.util.*;

class FlightPassenger {
    int id;
    String name;

    FlightPassenger(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Seat {
    String seatNumber;
    boolean booked;

    Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }
}

class Flight {
    HashMap<String, Seat> seats = new HashMap<>();
    HashMap<Integer, String> passengerSeatMap = new HashMap<>();

    Flight() {
        String[] rows = {"A", "B", "C", "D"};

        for (int i = 1; i <= 5; i++) {
            for (String row : rows) {
                String seatNo = i + row;
                seats.put(seatNo, new Seat(seatNo));
            }
        }
    }

    void bookSeat(FlightPassenger passenger, String seatNo) {
        if (!seats.containsKey(seatNo)) {
            System.out.println("Invalid seat");
            return;
        }

        Seat seat = seats.get(seatNo);

        if (seat.booked) {
            System.out.println("Seat already booked");
            return;
        }

        seat.booked = true;
        passengerSeatMap.put(passenger.id, seatNo);

        System.out.println(passenger.name + " booked seat " + seatNo);
    }

    void cancelSeat(int passengerId) {
        if (!passengerSeatMap.containsKey(passengerId)) {
            System.out.println("Passenger booking not found");
            return;
        }

        String seatNo = passengerSeatMap.get(passengerId);
        seats.get(seatNo).booked = false;
        passengerSeatMap.remove(passengerId);

        System.out.println("Seat " + seatNo + " cancelled");
    }

    void showAvailableSeats() {
        for (String seatNo : seats.keySet()) {
            if (!seats.get(seatNo).booked) {
                System.out.print(seatNo + " ");
            }
        }
        System.out.println();
    }
}

public class AeroplaneSeatAllocation {
    public static void main(String[] args) {
        Flight flight = new Flight();

        FlightPassenger p1 = new FlightPassenger(1, "Rahul");
        FlightPassenger p2 = new FlightPassenger(2, "Sahil");
        FlightPassenger p3 = new FlightPassenger(3, "Meena");

        flight.bookSeat(p1, "1A");
        flight.bookSeat(p2, "2B");
        flight.bookSeat(p3, "1A");

        flight.showAvailableSeats();

        flight.cancelSeat(1);

        flight.bookSeat(p3, "1A");

        flight.showAvailableSeats();
    }
}
