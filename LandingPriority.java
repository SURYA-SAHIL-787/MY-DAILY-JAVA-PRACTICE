class Aircraft {
    void landingPriority() {
        System.out.println("General landing priority");
    }
}

class PassengerAircraft extends Aircraft {
    @Override
    void landingPriority() {
        System.out.println("Passenger Aircraft: Medium Priority");
    }
}

class CargoAircraft extends Aircraft {
    @Override
    void landingPriority() {
        System.out.println("Cargo Aircraft: Normal Priority");
    }
}

class EmergencyAircraft extends Aircraft {
    @Override
    void landingPriority() {
        System.out.println("Emergency Aircraft: Highest Priority");
    }
}

public class LandingPriority {
    public static void main(String[] args) {

        Aircraft aircraft;

        aircraft = new PassengerAircraft();
        aircraft.landingPriority();

        aircraft = new CargoAircraft();
        aircraft.landingPriority();

        aircraft = new EmergencyAircraft();
        aircraft.landingPriority();
    }
}
