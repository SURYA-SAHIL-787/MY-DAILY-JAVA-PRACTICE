
import java.util.*;

public class APIRateLimiter {

    private final int maxRequests;
    private final long windowMillis;

    private final Map<String, Deque<Long>> requests =
            new HashMap<>();

    public APIRateLimiter(int maxRequests, long windowMillis) {
        if (maxRequests <= 0 || windowMillis <= 0)
            throw new IllegalArgumentException("Invalid limits");

        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    public synchronized boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();

        Deque<Long> timestamps = requests.computeIfAbsent(
                userId, key -> new ArrayDeque<>()
        );

        while (!timestamps.isEmpty()
                && now - timestamps.peekFirst() >= windowMillis) {
            timestamps.pollFirst();
        }

        if (timestamps.size() >= maxRequests) {
            return false;
        }

        timestamps.offerLast(now);
        return true;
    }

    public static void main(String[] args) {
        APIRateLimiter limiter = new APIRateLimiter(3, 60000);

        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest("user101");

            System.out.println("Request " + i + ": "
                    + (allowed ? "ALLOWED" : "REJECTED"));
        }
    }
}
