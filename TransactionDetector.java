
import java.util.*;

class TransactionMonitor {
    private Queue<Long> transactions = new ArrayDeque<>();

    public void record(long timestamp) {

        // Timestamps must arrive in chronological order.
        while (!transactions.isEmpty()
                && timestamp - transactions.peek() >= 60) {
            transactions.poll();
        }

        transactions.offer(timestamp);

        if (transactions.size() >= 3) {
            System.out.println(
                "ALERT: Suspicious transaction frequency"
            );
        } else {
            System.out.println("Transaction recorded");
        }
    }
}

public class TransactionDetector {
    public static void main(String[] args) {

        TransactionMonitor monitor = new TransactionMonitor();

        monitor.record(10);
        monitor.record(25);
        monitor.record(40);
        monitor.record(120);
        monitor.record(130);
    }
}
