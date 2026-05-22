import java.util.*;

class Passenger {
    int id;
    String name;
    int age;

    Passenger(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

class Ticket {
    Passenger passenger;
    int seatNumber;

    Ticket(Passenger passenger, int seatNumber) {
        this.passenger = passenger;
        this.seatNumber = seatNumber;
    }
}

class Train {
    Stack<Integer> availableSeats = new Stack<>();
    Queue<Passenger> waitingList = new LinkedList<>();
    ArrayList<Ticket> bookedTickets = new ArrayList<>();

    Train(int totalSeats) {
        for (int i = totalSeats; i >= 1; i--) {
            availableSeats.push(i);
        }
    }

    void bookTicket(Passenger p) {
        if (!availableSeats.isEmpty()) {
            int seat = availableSeats.pop();
            bookedTickets.add(new Ticket(p, seat));
            System.out.println(p.name + " booked seat " + seat);
        } else {
            waitingList.add(p);
            System.out.println(p.name + " added to waiting list");
        }
    }

    void cancelTicket(int passengerId) {
        for (int i = 0; i < bookedTickets.size(); i++) {
            Ticket t = bookedTickets.get(i);

            if (t.passenger.id == passengerId) {
                int seat = t.seatNumber;
                System.out.println(t.passenger.name + " cancelled seat " + seat);
                bookedTickets.remove(i);

                if (!waitingList.isEmpty()) {
                    Passenger next = waitingList.poll();
                    bookedTickets.add(new Ticket(next, seat));
                    System.out.println(next.name + " got confirmed seat " + seat);
                } else {
                    availableSeats.push(seat);
                }
                return;
            }
        }

        System.out.println("Passenger not found");
    }

    void showTickets() {
        for (Ticket t : bookedTickets) {
            System.out.println(t.passenger.name + " Seat: " + t.seatNumber);
        }
    }
}

public class RailwayReservationSystem {
    public static void main(String[] args) {
        Train train = new Train(3);

        train.bookTicket(new Passenger(1, "Arjun", 20));
        train.bookTicket(new Passenger(2, "Kiran", 22));
        train.bookTicket(new Passenger(3, "Ravi", 21));
        train.bookTicket(new Passenger(4, "Sahil", 18));
        train.bookTicket(new Passenger(5, "Vikram", 23));

        train.showTickets();

        train.cancelTicket(2);

        train.showTickets();
    }
}
