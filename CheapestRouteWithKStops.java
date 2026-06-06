import java.util.*;

public class CheapestRouteWithKStops {
    static class Route {
        int city, cost, stops;

        Route(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public static int findCheapestRoute(int n, int[][] roads, int source, int destination, int k) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
        }

        PriorityQueue<Route> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Route(source, 0, 0));

        while (!pq.isEmpty()) {
            Route current = pq.poll();

            if (current.city == destination) {
                return current.cost;
            }

            if (current.stops <= k) {
                for (int[] next : graph.get(current.city)) {
                    int nextCity = next[0];
                    int price = next[1];

                    pq.add(new Route(nextCity, current.cost + price, current.stops + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 5;

        int[][] roads = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 4, 100},
            {0, 3, 500},
            {3, 4, 100}
        };

        int source = 0;
        int destination = 4;
        int k = 2;

        int result = findCheapestRoute(n, roads, source, destination, k);

        System.out.println("Cheapest route cost: " + result);
    }
}
