import java.util.ArrayList;

class Runway {
    int runwayNumber;
    String status;

    Runway(int runwayNumber, String status) {
        this.runwayNumber = runwayNumber;
        this.status = status;
    }
}

public class RunwayAllocation {

    public static void main(String[] args) {

        ArrayList<Runway> runways = new ArrayList<>();

        runways.add(new Runway(1, "Occupied"));
        runways.add(new Runway(2, "Available"));
        runways.add(new Runway(3, "Available"));

        System.out.println("Airport Runway Status:");

        for (Runway runway : runways) {

            System.out.println(
                "Runway " +
                runway.runwayNumber +
                " - " +
                runway.status
            );
        }
    }
}
