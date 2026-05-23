import java.util.*;
import java.util.concurrent.*;

class InvalidGraphException extends Exception {
    InvalidGraphException(String message) {
        super(message);
    }
}

class ParallelGraph {
    private final Map<Integer, List<Integer>> graph;
    private final Set<Integer> visited;
    private final ExecutorService executor;

    public ParallelGraph(int threads) {
        this.graph = new ConcurrentHashMap<>();
        this.visited = ConcurrentHashMap.newKeySet();
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void addEdge(int u, int v) throws InvalidGraphException {
        if (u < 0 || v < 0) {
            throw new InvalidGraphException("Node values cannot be negative");
        }

        graph.putIfAbsent(u, Collections.synchronizedList(new ArrayList<>()));
        graph.putIfAbsent(v, Collections.synchronizedList(new ArrayList<>()));

        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public void parallelBFS(int start) throws InvalidGraphException, InterruptedException {
        if (!graph.containsKey(start)) {
            throw new InvalidGraphException("Start node does not exist");
        }

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            CountDownLatch latch = new CountDownLatch(levelSize);

            for (int i = 0; i < levelSize; i++) {
                Integer node = queue.poll();

                if (node == null) {
                    latch.countDown();
                    continue;
                }

                executor.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " visited " + node);

                        for (Integer neighbor : graph.get(node)) {
                            if (visited.add(neighbor)) {
                                queue.add(neighbor);
                            }
                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();
        }
    }

    public int countConnectedComponents() throws InterruptedException {
        visited.clear();
        int components = 0;

        for (Integer node : graph.keySet()) {
            if (visited.add(node)) {
                components++;
                Queue<Integer> queue = new ConcurrentLinkedQueue<>();
                queue.add(node);

                while (!queue.isEmpty()) {
                    Integer current = queue.poll();

                    if (current == null) {
                        continue;
                    }

                    for (Integer neighbor : graph.get(current)) {
                        if (visited.add(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        return components;
    }

    public void shutdown() {
        executor.shutdown();
    }
}

public class ParallelGraphTraversal {
    public static void main(String[] args) throws Exception {
        ParallelGraph graph = new ParallelGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(5, 6);

        graph.parallelBFS(0);

        System.out.println("Connected components: " + graph.countConnectedComponents());

        graph.shutdown();
    }
}
