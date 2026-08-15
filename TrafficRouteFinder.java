import java.util.*;

class Road {
    int destination;
    int time;

    Road(int destination, int time) {
        this.destination = destination;
        this.time = time;
    }
}

class Junction implements Comparable<Junction> {
    int vertex;
    int distance;

    Junction(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Junction other) {
        return this.distance - other.distance;
    }
}

public class TrafficRouteFinder {

    static void dijkstra(List<List<Road>> graph, int source) {

        int n = graph.size();

        int[] distance = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;

        PriorityQueue<Junction> pq = new PriorityQueue<>();

        pq.add(new Junction(source, 0));

        while (!pq.isEmpty()) {

            Junction current = pq.poll();

            int currentVertex = current.vertex;

            if (current.distance > distance[currentVertex]) {
                continue;
            }

            for (Road road : graph.get(currentVertex)) {

                int newDistance =
                        distance[currentVertex] + road.time;

                if (newDistance < distance[road.destination]) {

                    distance[road.destination] = newDistance;

                    pq.add(
                            new Junction(
                                    road.destination,
                                    newDistance
                            )
                    );
                }
            }
        }

        System.out.println("Shortest Travel Time:");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "Junction " + source +
                    " -> Junction " + i +
                    " = " + distance[i] + " minutes"
            );
        }
    }

    public static void main(String[] args) {

        int junctions = 5;

        List<List<Road>> graph = new ArrayList<>();

        for (int i = 0; i < junctions; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Road(1, 4));
        graph.get(0).add(new Road(2, 2));

        graph.get(1).add(new Road(2, 1));
        graph.get(1).add(new Road(3, 5));

        graph.get(2).add(new Road(3, 8));
        graph.get(2).add(new Road(4, 10));

        graph.get(3).add(new Road(4, 2));

        dijkstra(graph, 0);
    }
}
