class Aircraft {
    private String flightNumber;
    private int speed;
    private int altitude;

    Aircraft(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        } else {
            System.out.println("Invalid speed");
        }
    }

    public void setAltitude(int altitude) {
        if (altitude >= 0) {
            this.altitude = altitude;
        } else {
            System.out.println("Invalid altitude");
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getAltitude() {
        return altitude;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    void displayStatus() {
        System.out.println("Flight Number : " + getFlightNumber());
        System.out.println("Speed         : " + getSpeed() + " km/h");
        System.out.println("Altitude      : " + getAltitude() + " ft");
    }
}

public class AircraftControl {
    public static void main(String[] args) {

        Aircraft aircraft = new Aircraft("AI450");

        aircraft.setSpeed(850);
        aircraft.setAltitude(30000);

        aircraft.displayStatus();
    }
}
