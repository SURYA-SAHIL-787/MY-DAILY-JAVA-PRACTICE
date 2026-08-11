import java.util.*;

class Delivery implements Comparable<Delivery> {

    private int orderId;
    private String destination;
    private int priority;

    public Delivery(int orderId, String destination, int priority) {
        this.orderId = orderId;
        this.destination = destination;
        this.priority = priority;
    }

    @Override
    public int compareTo(Delivery other) {
        return Integer.compare(this.priority, other.priority);
    }

    public void displayDelivery() {
        System.out.println(
            "Order ID: " + orderId +
            ", Destination: " + destination +
            ", Priority: " + priority
        );
    }
}

class DeliveryManager {

    private PriorityQueue<Delivery> deliveryQueue;

    public DeliveryManager() {
        deliveryQueue = new PriorityQueue<>();
    }

    public void addDelivery(Delivery delivery) {
        deliveryQueue.offer(delivery);
    }

    public void processDeliveries() {

        System.out.println("Delivery Processing Order:");

        while (!deliveryQueue.isEmpty()) {
            Delivery delivery = deliveryQueue.poll();
            delivery.displayDelivery();
        }
    }
}

public class DeliveryPriorityManager {

    public static void main(String[] args) {

        DeliveryManager manager = new DeliveryManager();

        manager.addDelivery(
            new Delivery(101, "Chennai", 3)
        );

        manager.addDelivery(
            new Delivery(102, "Bangalore", 1)
        );

        manager.addDelivery(
            new Delivery(103, "Hyderabad", 4)
        );

        manager.addDelivery(
            new Delivery(104, "Mumbai", 2)
        );

        manager.processDeliveries();
    }
}
