class Aircraft {
    String flightNumber;
    int maxSpeed;

    Aircraft(String flightNumber, int maxSpeed) {
        this.flightNumber = flightNumber;
        this.maxSpeed = maxSpeed;
    }

    void displayDetails() {
        System.out.println("Flight Number : " + flightNumber);
        System.out.println("Maximum Speed : " + maxSpeed + " km/h");
    }
}

class PassengerPlane extends Aircraft {
    int passengerCapacity;

    PassengerPlane(String flightNumber, int maxSpeed, int passengerCapacity) {
        super(flightNumber, maxSpeed);
        this.passengerCapacity = passengerCapacity;
    }

    void showPassengerPlane() {
        displayDetails();
        System.out.println("Passenger Capacity : " + passengerCapacity);
    }
}

class CargoPlane extends Aircraft {
    int cargoCapacity;

    CargoPlane(String flightNumber, int maxSpeed, int cargoCapacity) {
        super(flightNumber, maxSpeed);
        this.cargoCapacity = cargoCapacity;
    }

    void showCargoPlane() {
        displayDetails();
        System.out.println("Cargo Capacity : " + cargoCapacity + " kg");
    }
}

public class AircraftInheritance {
    public static void main(String[] args) {

        PassengerPlane p = new PassengerPlane("AI101", 900, 180);
        CargoPlane c = new CargoPlane("CG501", 750, 50000);

        System.out.println("Passenger Aircraft");
        p.showPassengerPlane();

        System.out.println();

        System.out.println("Cargo Aircraft");
        c.showCargoPlane();
    }
}
