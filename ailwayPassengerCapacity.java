public class RailwayPassengerCapacity {

    public static void main(String[] args) {

        int capacity = 100;

        int[] boarding = {
                50, 30, 20, 10
        };

        int[] leaving = {
                0, 10, 30, 20
        };

        int passengers = 0;
        int maximumPassengers = 0;

        boolean valid = true;

        for (int i = 0; i < boarding.length; i++) {

            passengers -= leaving[i];

            passengers += boarding[i];

            maximumPassengers =
                    Math.max(
                            maximumPassengers,
                            passengers);

            if (passengers > capacity) {
                valid = false;
            }

            System.out.println(
                    "Station " + (i + 1) +
                    " -> Passengers: " +
                    passengers);
        }

        System.out.println(
                "\nMaximum passengers = "
                        + maximumPassengers);

        if (valid) {
            System.out.println(
                    "Valid = YES");
        } else {
            System.out.println(
                    "Valid = NO");
        }
    }
}
