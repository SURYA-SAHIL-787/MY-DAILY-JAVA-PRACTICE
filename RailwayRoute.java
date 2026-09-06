import java.util.*;

public class RailwayRoute {

    static class Edge {
        int destination;
        int time;

        Edge(int destination, int time) {
            this.destination = destination;
            this.time = time;
        }
    }

    static class Node implements Comparable<Node> {
        int station;
        int distance;

        Node(int station, int distance) {
            this.station = station;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static int shortestPath(
            int n,
            ArrayList<Edge>[] graph,
            int source,
            int destination) {

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[source] = 0;
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > distance[current.station]) {
                continue;
            }

            for (Edge edge : graph[current.station]) {
                int newDistance =
                        current.distance + edge.time;

                if (newDistance < distance[edge.destination]) {
                    distance[edge.destination] = newDistance;

                    pq.add(new Node(
                            edge.destination,
                            newDistance));
                }
            }
        }

        return distance[destination];
    }

    public static void main(String[] args) {

        int n = 6;

        ArrayList<Edge>[] graph =
                new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        addRoute(graph, 1, 2, 4);
        addRoute(graph, 1, 3, 2);
        addRoute(graph, 3, 2, 1);
        addRoute(graph, 2, 4, 5);
        addRoute(graph, 3, 5, 3);
        addRoute(graph, 5, 4, 1);

        int source = 1;
        int destination = 4;

        int result =
                shortestPath(n, graph, source, destination);

        System.out.println(
                "Minimum travel time = " + result);
    }

    static void addRoute(
            ArrayList<Edge>[] graph,
            int from,
            int to,
            int time) {

        graph[from].add(new Edge(to, time));
        graph[to].add(new Edge(from, time));
    }
}
