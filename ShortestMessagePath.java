import java.util.*;

public class ShortestMessagePath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int answer = bfsShortestPath(graph, n);
        System.out.println(answer);

        sc.close();
    }

    static int bfsShortestPath(ArrayList<ArrayList<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];

        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;
        distance[0] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbour : graph.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    distance[neighbour] = distance[node] + 1;
                    queue.add(neighbour);
                }
            }
        }

        return distance[n - 1];
    }
}
