import java.util.*;

class Train {
    private int trainNumber;
    private String trainName;
    private String destination;

    public Train(int trainNumber, String trainName, String destination) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.destination = destination;
    }

    public void displayTrain() {
        System.out.println(
            trainNumber + " - " + trainName + " - " + destination
        );
    }
}

public class TrainArrivalQueue {
    public static void main(String[] args) {

        Queue<Train> trainQueue = new LinkedList<>();

        trainQueue.add(new Train(12627, "Karnataka Express", "New Delhi"));
        trainQueue.add(new Train(12658, "Chennai Mail", "Chennai"));
        trainQueue.add(new Train(16591, "Hampi Express", "Hubballi"));

        System.out.println("Trains Waiting:");

        for (Train train : trainQueue) {
            train.displayTrain();
        }

        System.out.println("\nTrain Departure Order:");

        while (!trainQueue.isEmpty()) {
            Train train = trainQueue.poll();
            train.displayTrain();
        }
    }
}
