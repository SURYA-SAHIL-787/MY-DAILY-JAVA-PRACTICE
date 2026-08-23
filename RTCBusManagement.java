import java.util.*;

class Bus {

    protected String busNumber;
    protected String route;

    public Bus(String busNumber, String route) {
        this.busNumber = busNumber;
        this.route = route;
    }

    public void displayDetails() {
        System.out.println("RTC Bus");
    }
}

class ExpressBus extends Bus {

    public ExpressBus(String busNumber, String route) {
        super(busNumber, route);
    }

    @Override
    public void displayDetails() {
        System.out.println(
            busNumber + " | " + route + " | Express Bus"
        );
    }
}

class SuperLuxuryBus extends Bus {

    public SuperLuxuryBus(String busNumber, String route) {
        super(busNumber, route);
    }

    @Override
    public void displayDetails() {
        System.out.println(
            busNumber + " | " + route + " | Super Luxury Bus"
        );
    }
}

class OrdinaryBus extends Bus {

    public OrdinaryBus(String busNumber, String route) {
        super(busNumber, route);
    }

    @Override
    public void displayDetails() {
        System.out.println(
            busNumber + " | " + route + " | Ordinary Bus"
        );
    }
}

public class RTCBusManagement {

    public static void main(String[] args) {

        ArrayList<Bus> buses = new ArrayList<>();

        buses.add(new ExpressBus("TS09Z1234", "Hyderabad - Warangal"));
        buses.add(new SuperLuxuryBus("TS08Z5678", "Hyderabad - Vijayawada"));
        buses.add(new OrdinaryBus("TS10Z9012", "Hyderabad - Sangareddy"));

        System.out.println("RTC Bus Details:");

        for (Bus bus : buses) {
            bus.displayDetails();
        }
    }
}
