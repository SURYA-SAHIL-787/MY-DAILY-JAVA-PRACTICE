class Passenger {
    int id;
    String name;
    String destination;
    Passenger next;

    Passenger(int id, String name, String destination) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.next = null;
    }
}

public class RailwayPassengerList {

    Passenger head;

    void addPassenger(int id, String name, String destination) {
        Passenger newPassenger =
                new Passenger(id, name, destination);

        if (head == null) {
            head = newPassenger;
        } else {
            Passenger temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newPassenger;
        }
    }

    void displayPassengers() {
        Passenger temp = head;

        while (temp != null) {
            System.out.println("ID: " + temp.id);
            System.out.println("Name: " + temp.name);
            System.out.println("Destination: " + temp.destination);
            System.out.println();

            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        RailwayPassengerList railway =
                new RailwayPassengerList();

        railway.addPassenger(101, "Arun", "Delhi");
        railway.addPassenger(102, "Meena", "Chennai");
        railway.addPassenger(103, "Rahul", "Mumbai");

        System.out.println("Passenger List:");
        railway.displayPassengers();
    }
}
