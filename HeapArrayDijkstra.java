import java.util.*;

public class HeapArrayDijkstra {
    static class Pair {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static void dijkstra(ArrayList<ArrayList<Pair>> graph, int src) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a.dist)
        );

        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            for (Pair edge : graph.get(curr.node)) {
                if (dist[curr.node] + edge.dist < dist[edge.node]) {
                    dist[edge.node] = dist[curr.node] + edge.dist;
                    pq.add(new Pair(edge.node, dist[edge.node]));
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }

    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Pair(1, 2));
        graph.get(0).add(new Pair(2, 4));
        graph.get(1).add(new Pair(2, 1));
        graph.get(1).add(new Pair(3, 7));
        graph.get(2).add(new Pair(4, 3));

        dijkstra(graph, 0);
    }
}
