import java.util.Stack;

public class GraphDFSStack {

    public static void main(String[] args) {

        int[][] graph = {
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 1, 0}
        };

        int start = 0;
        boolean[] visited = new boolean[graph.length];

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        System.out.print("DFS Traversal: ");

        while (!stack.isEmpty()) {

            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");

                // Add adjacent vertices to stack
                for (int i = graph.length - 1; i >= 0; i--) {
                    if (graph[current][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }
}
