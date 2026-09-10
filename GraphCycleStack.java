import java.util.*;

public class GraphCycleStack {

    static class Pair {
        int node;
        int parent;

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {0, 0, 1, 0}
        };

        int n = graph.length;
        boolean[] visited = new boolean[n];

        Stack<Pair> stack = new Stack<>();

        boolean cycleFound = false;

        stack.push(new Pair(0, -1));

        while (!stack.isEmpty()) {

            Pair current = stack.pop();

            int node = current.node;
            int parent = current.parent;

            if (visited[node]) {
                continue;
            }

            visited[node] = true;

            for (int i = 0; i < n; i++) {

                if (graph[node][i] == 1) {

                    if (!visited[i]) {
                        stack.push(new Pair(i, node));
                    }
                    else if (i != parent) {
                        cycleFound = true;
                        break;
                    }
                }
            }

            if (cycleFound) {
                break;
            }
        }

        if (cycleFound) {
            System.out.println("Cycle exists in the graph");
        } else {
            System.out.println("No cycle exists");
        }
    }
}
