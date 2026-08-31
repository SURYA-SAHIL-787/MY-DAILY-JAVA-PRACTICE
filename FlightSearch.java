import java.util.Arrays;

class FlightDatabase {

    String[] flights;

    FlightDatabase(String[] flights) {
        this.flights = flights;
        Arrays.sort(this.flights);
    }

    int search(String flightNumber) {

        int low = 0;
        int high = flights.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result =
                flights[mid].compareToIgnoreCase(flightNumber);

            if (result == 0) {
                return mid;
            }

            if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}

public class FlightSearch {

    public static void main(String[] args) {

        String[] flights = {
            "AI101",
            "6E202",
            "UK303",
            "SG404",
            "AI505"
        };

        FlightDatabase database =
            new FlightDatabase(flights);

        String target = "UK303";

        int result = database.search(target);

        if (result != -1) {
            System.out.println(target + " found.");
        } else {
            System.out.println(target + " not found.");
        }
    }
}
