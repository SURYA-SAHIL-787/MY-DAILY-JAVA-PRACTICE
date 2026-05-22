import java.util.*;

class CityMap {
    int vertices;
    ArrayList<ArrayList<int[]>> graph;

    CityMap(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    void addRoad(int source, int destination, int distance) {
        graph.get(source).add(new int[]{destination, distance});
        graph.get(destination).add(new int[]{source, distance});
    }

    void shortestPath(int start) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        distance[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];

            if (dist > distance[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int weight = edge[1];

                if (distance[node] + weight < distance[next]) {
                    distance[next] = distance[node] + weight;
                    pq.add(new int[]{next, distance[next]});
                }
            }
        }

        for (int i = 0; i < vertices; i++) {
            System.out.println("Shortest distance from ambulance station to area " + i + " = " + distance[i]);
        }
    }
}

public class AmbulanceRouteSystem {
    public static void main(String[] args) {
        CityMap map = new CityMap(6);

        map.addRoad(0, 1, 4);
        map.addRoad(0, 2, 2);
        map.addRoad(1, 2, 1);
        map.addRoad(1, 3, 5);
        map.addRoad(2, 3, 8);
        map.addRoad(2, 4, 10);
        map.addRoad(3, 4, 2);
        map.addRoad(3, 5, 6);
        map.addRoad(4, 5, 3);

        map.shortestPath(0);
    }
}
