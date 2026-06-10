import java.util.*;

public class ArrayGraphBFS {
    static void bfs(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);
        graph.get(4).add(5);

        bfs(graph, 0);
    }
}
