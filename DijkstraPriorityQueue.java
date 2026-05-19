import java.util.*;

class Node implements Comparable<Node> {
    int vertex, cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}

public class DijkstraPriorityQueue {

    static void dijkstra(int[][] graph, int src) {

        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            for (int i = 0; i < V; i++) {

                if (graph[current.vertex][i] != 0) {

                    int newDist = dist[current.vertex] + graph[current.vertex][i];

                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        pq.add(new Node(i, newDist));
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println("Distance from 0 to " + i + " is " + dist[i]);
        }
    }

    public static void main(String[] args) {

        int[][] graph = {
                {0, 4, 2, 0},
                {4, 0, 1, 5},
                {2, 1, 0, 8},
                {0, 5, 8, 0}
        };

        dijkstra(graph, 0);
    }
}
