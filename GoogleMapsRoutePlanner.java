import java.util.*;

public class GoogleMapsRoutePlanner {
    static class Edge {
        String city;
        int distance;

        Edge(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    static class Node {
        String city;
        int distance;

        Node(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    public static void dijkstra(Map<String, List<Edge>> graph, String source) {
        Map<String, Integer> distances = new HashMap<>();

        for (String city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }

        distances.put(source, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Edge edge : graph.get(current.city)) {
                int newDistance = current.distance + edge.distance;

                if (newDistance < distances.get(edge.city)) {
                    distances.put(edge.city, newDistance);
                    pq.add(new Node(edge.city, newDistance));
                }
            }
        }

        for (String city : distances.keySet()) {
            System.out.println("Shortest distance from " + source + " to " + city + ": " + distances.get(city));
        }
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();

        graph.put("Home", new ArrayList<>());
        graph.put("School", new ArrayList<>());
        graph.put("Mall", new ArrayList<>());
        graph.put("Hospital", new ArrayList<>());
        graph.put("Station", new ArrayList<>());

        graph.get("Home").add(new Edge("School", 4));
        graph.get("Home").add(new Edge("Mall", 2));

        graph.get("School").add(new Edge("Hospital", 5));
        graph.get("Mall").add(new Edge("School", 1));
        graph.get("Mall").add(new Edge("Station", 7));
        graph.get("Hospital").add(new Edge("Station", 3));

        graph.get("School").add(new Edge("Home", 4));
        graph.get("Mall").add(new Edge("Home", 2));
        graph.get("Hospital").add(new Edge("School", 5));
        graph.get("School").add(new Edge("Mall", 1));
        graph.get("Station").add(new Edge("Mall", 7));
        graph.get("Station").add(new Edge("Hospital", 3));

        dijkstra(graph, "Home");
    }
}
