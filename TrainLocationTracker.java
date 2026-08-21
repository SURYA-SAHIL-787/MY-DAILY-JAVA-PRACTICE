import java.util.*;

class TrainLocation {

    private int trainNumber;
    private String station;

    public TrainLocation(int trainNumber, String station) {
        this.trainNumber = trainNumber;
        this.station = station;
    }

    public void displayLocation() {
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Current Station: " + station);
    }
}

public class TrainLocationTracker {

    public static void main(String[] args) {

        HashMap<Integer, TrainLocation> trains = new HashMap<>();

        trains.put(
            12627,
            new TrainLocation(12627, "Bengaluru")
        );

        trains.put(
            12658,
            new TrainLocation(12658, "Chennai")
        );

        trains.put(
            16591,
            new TrainLocation(16591, "Mysuru")
        );

        int searchTrain = 12627;

        if (trains.containsKey(searchTrain)) {

            System.out.println("Train Found:");
            trains.get(searchTrain).displayLocation();

        } else {

            System.out.println("Train Not Found");
        }
    }
}
