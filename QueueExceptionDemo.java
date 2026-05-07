import java.util.LinkedList;
import java.util.Queue;

public class QueueExceptionDemo {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        try {
            System.out.println(queue.remove());
        } catch (Exception e) {
            System.out.println("Queue is empty.");
            System.out.println(e);
        }
    }
}
