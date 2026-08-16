abstract class Flight {
    String flightNumber;
    int altitude;
    int speed;

    Flight(String flightNumber, int altitude, int speed) {
        this.flightNumber = flightNumber;
        this.altitude = altitude;
        this.speed = speed;
    }

    abstract void requestLanding();

    void displayDetails() {
        System.out.println("Flight Number : " + flightNumber);
        System.out.println("Altitude      : " + altitude + " ft");
        System.out.println("Speed         : " + speed + " km/h");
    }
}

class DomesticFlight extends Flight {

    DomesticFlight(String flightNumber, int altitude, int speed) {
        super(flightNumber, altitude, speed);
    }

    @Override
    void requestLanding() {
        System.out.println("Domestic flight requesting landing.");
    }
}

class InternationalFlight extends Flight {

    InternationalFlight(String flightNumber, int altitude, int speed) {
        super(flightNumber, altitude, speed);
    }

    @Override
    void requestLanding() {
        System.out.println("International flight requesting landing.");
    }
}

class AirTrafficController {

    void givePermission(Flight flight) {

        flight.displayDetails();
        flight.requestLanding();

        if (flight.altitude <= 10000) {
            System.out.println("Status: Cleared for Landing");
        } else {
            System.out.println("Status: Waiting for Clearance");
        }

        System.out.println();
    }
}

public class AirTrafficControlSystem {
    public static void main(String[] args) {

        Flight f1 = new DomesticFlight("AI101", 8000, 450);
        Flight f2 = new InternationalFlight("EK505", 15000, 600);

        AirTrafficController controller = new AirTrafficController();

        controller.givePermission(f1);
        controller.givePermission(f2);
    }
}
