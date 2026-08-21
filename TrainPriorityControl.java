import java.util.*;

class PriorityTrain implements Comparable<PriorityTrain> {

    private String trainName;
    private int priority;

    public PriorityTrain(String trainName, int priority) {
        this.trainName = trainName;
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityTrain other) {
        return this.priority - other.priority;
    }

    public void displayTrain() {
        System.out.println(
            trainName + " - Priority: " + priority
        );
    }
}

public class TrainPriorityControl {

    public static void main(String[] args) {

        PriorityQueue<PriorityTrain> queue = new PriorityQueue<>();

        queue.add(new PriorityTrain("Passenger Train", 3));
        queue.add(new PriorityTrain("Express Train", 2));
        queue.add(new PriorityTrain("Emergency Relief Train", 1));
        queue.add(new PriorityTrain("Goods Train", 4));

        System.out.println("Train Clearance Order:");

        while (!queue.isEmpty()) {
            queue.poll().displayTrain();
        }
    }
}
