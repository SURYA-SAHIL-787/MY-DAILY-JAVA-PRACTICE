import java.util.*;

class Patient {
    private String name;
    private int priority;
    private long arrivalOrder;

    public Patient(String name, int priority, long arrivalOrder) {
        this.name = name;
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public long getArrivalOrder() { return arrivalOrder; }
}

public class EmergencyQueue {
    public static void main(String[] args) {

        PriorityQueue<Patient> queue = new PriorityQueue<>(
            Comparator.comparingInt(Patient::getPriority)
                .reversed()
                .thenComparingLong(Patient::getArrivalOrder)
        );

        queue.add(new Patient("Arun", 2, 1));
        queue.add(new Patient("Priya", 5, 2));
        queue.add(new Patient("Kiran", 4, 3));
        queue.add(new Patient("Meena", 5, 4));

        while (!queue.isEmpty()) {
            Patient patient = queue.poll();
            System.out.println("Next: " + patient.getName());
        }
    }
}
