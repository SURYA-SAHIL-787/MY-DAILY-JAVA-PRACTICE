import java.util.HashMap;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class SlidingWindowRateLimiter {

    private final int requestLimit;
    private final int windowSize;

    private final Map<String, Deque<Long>> requests =
            new HashMap<>();

    public SlidingWindowRateLimiter(int requestLimit,
                                    int windowSize) {

        this.requestLimit = requestLimit;
        this.windowSize = windowSize;
    }

    public boolean allowRequest(String userId, long timestamp) {

        Deque<Long> queue = requests.computeIfAbsent(
                userId, key -> new ArrayDeque<>());

        // Remove expired requests
        while (!queue.isEmpty()
                && queue.peekFirst() <= timestamp - windowSize) {

            queue.pollFirst();
        }

        // Reject if the limit is reached
        if (queue.size() >= requestLimit) {
            return false;
        }

        queue.offerLast(timestamp);

        return true;
    }

    public static void main(String[] args) {

        SlidingWindowRateLimiter limiter =
                new SlidingWindowRateLimiter(100, 60);

        String[] users = {"a", "b", "a", "a"};
        long[] timestamps = {0, 0, 59, 60};

        boolean[] result = new boolean[users.length];

        for (int i = 0; i < users.length; i++) {
            result[i] = limiter.allowRequest(
                    users[i], timestamps[i]);
        }

        System.out.println(Arrays.toString(result));
    }
}
