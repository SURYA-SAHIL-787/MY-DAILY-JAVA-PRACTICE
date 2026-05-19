import java.util.*;

class Edge {
    int target, weight;

    Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class DijkstraAdjacencyList {

    static void dijkstra(List<List<Edge>> graph, int source) {

        int V = graph.size();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();
            int u = current[0];

            for (Edge edge : graph.get(u)) {

                int v = edge.target;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {

                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println("Distance to " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {

        int V = 4;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(3, 1));
        graph.get(2).add(new Edge(3, 5));

        dijkstra(graph, 0);
    }
}
