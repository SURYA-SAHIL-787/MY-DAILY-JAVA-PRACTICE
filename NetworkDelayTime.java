import java.util.*;

public class NetworkDelayTime {

    static class Pair {
        int node;
        int time;

        Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    public static int networkDelayTime(int[][] times, int n, int source) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new Pair(v, w));
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.time - b.time);

        distance[source] = 0;
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            int node = current.node;
            int time = current.time;

            if (time > distance[node]) {
                continue;
            }

            for (Pair neighbor : graph.get(node)) {
                int newTime = time + neighbor.time;

                if (newTime < distance[neighbor.node]) {
                    distance[neighbor.node] = newTime;
                    pq.add(new Pair(neighbor.node, newTime));
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, distance[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        int n = 4;
        int source = 2;

        System.out.println("Network Delay Time: " + networkDelayTime(times, n, source));
    }
}
