
import java.util.*;

public class TopKAPIEndpoints {

    public static List<String> topKEndpoints(
            List<String> logs, int k) {

        Map<String, Integer> frequency = new HashMap<>();

        for (String endpoint : logs) {
            frequency.put(endpoint,
                    frequency.getOrDefault(endpoint, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (a, b) -> {
                    int compare = Integer.compare(
                            frequency.get(a), frequency.get(b));

                    if (compare != 0) return compare;

                    return b.compareTo(a);
                }
        );

        for (String endpoint : frequency.keySet()) {
            minHeap.offer(endpoint);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>(minHeap);

        result.sort((a, b) -> {
            int compare = Integer.compare(
                    frequency.get(b), frequency.get(a));

            if (compare != 0) return compare;

            return a.compareTo(b);
        });

        return result;
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList(
                "/users", "/orders", "/users",
                "/products", "/users", "/orders",
                "/login", "/orders", "/users"
        );

        List<String> result = topKEndpoints(logs, 2);

        System.out.println(result);
    }
}
