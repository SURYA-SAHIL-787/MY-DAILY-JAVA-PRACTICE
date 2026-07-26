import java.util.*;

class DeliveryOrder implements Comparable<DeliveryOrder> {
    private final int orderId;
    private final int priority;
    private final double distance;

    DeliveryOrder(int orderId, int priority, double distance) {
        this.orderId = orderId;
        this.priority = priority;
        this.distance = distance;
    }

    int getOrderId() {
        return orderId;
    }

    @Override
    public int compareTo(DeliveryOrder other) {
        if (priority != other.priority) {
            return Integer.compare(other.priority, priority);
        }

        return Double.compare(distance, other.distance);
    }

    @Override
    public String toString() {
        return "Order " + orderId
                + ", priority=" + priority
                + ", distance=" + distance + " km";
    }
}

public class FoodDeliveryScheduler {
    private final PriorityQueue<DeliveryOrder> orders =
            new PriorityQueue<>();

    void addOrder(int id, int priority, double distance) {
        orders.offer(new DeliveryOrder(id, priority, distance));
    }

    void deliverNextOrder() {
        if (orders.isEmpty()) {
            System.out.println("No pending orders");
            return;
        }

        DeliveryOrder next = orders.poll();
        System.out.println("Delivering: " + next);
    }

    void showPendingOrders() {
        PriorityQueue<DeliveryOrder> copy =
                new PriorityQueue<>(orders);

        while (!copy.isEmpty()) {
            System.out.println(copy.poll());
        }
    }

    public static void main(String[] args) {
        FoodDeliveryScheduler scheduler =
                new FoodDeliveryScheduler();

        scheduler.addOrder(101, 1, 2.5);
        scheduler.addOrder(102, 3, 5.0);
        scheduler.addOrder(103, 3, 1.5);

        scheduler.showPendingOrders();
        scheduler.deliverNextOrder();
    }
}
