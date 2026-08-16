class Aeroplane {
    String flightNumber;
    String airlineName;
    String source;
    String destination;
    int altitude;

    Aeroplane(String flightNumber, String airlineName, String source,
              String destination, int altitude) {
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.altitude = altitude;
    }

    void displayDetails() {
        System.out.println("Flight Number : " + flightNumber);
        System.out.println("Airline Name  : " + airlineName);
        System.out.println("Source        : " + source);
        System.out.println("Destination   : " + destination);
        System.out.println("Altitude      : " + altitude + " ft");
        System.out.println();
    }
}

public class AeroplaneDetails {
    public static void main(String[] args) {

        Aeroplane a1 = new Aeroplane(
                "AI101", "Air India", "Bangalore", "Delhi", 30000);

        Aeroplane a2 = new Aeroplane(
                "IG202", "IndiGo", "Chennai", "Mumbai", 28000);

        Aeroplane a3 = new Aeroplane(
                "AI303", "Air India", "Hyderabad", "Kolkata", 32000);

        a1.displayDetails();
        a2.displayDetails();
        a3.displayDetails();
    }
}
