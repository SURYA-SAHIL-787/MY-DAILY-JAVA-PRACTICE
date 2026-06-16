import java.util.*;

public class DetectCycleGraph {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static boolean dfs(int node, int parent) {
        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                if (dfs(next, node)) {
                    return true;
                }
            } else if (next != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter edges:");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean hasCycle = false;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) {
            System.out.println("Graph has a cycle");
        } else {
            System.out.println("Graph does not have a cycle");
        }

        sc.close();
    }
}
