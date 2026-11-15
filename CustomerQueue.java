import java.util.ArrayDeque;
import java.util.Queue;

public class CustomerQueue {
    public static void main(String[] args) {
        Queue<String> customers = new ArrayDeque<>();

        customers.offer("Anita");
        customers.offer("Rahul");
        customers.offer("Meera");

        System.out.println("Served " + customers.poll());
        System.out.println("Next " + customers.peek());
    }
}
