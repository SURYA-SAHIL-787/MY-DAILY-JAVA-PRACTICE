import java.util.*;

public class ShortestPathBFS {
    static int[] shortestPath(int vertices, ArrayList<ArrayList<Integer>> graph, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();

        distance[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int vertices = 6;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(1).add(0);

        graph.get(0).add(2);
        graph.get(2).add(0);

        graph.get(1).add(3);
        graph.get(3).add(1);

        graph.get(2).add(4);
        graph.get(4).add(2);

        graph.get(4).add(5);
        graph.get(5).add(4);

        int[] distance = shortestPath(vertices, graph, 0);

        System.out.println(Arrays.toString(distance));
    }
}
