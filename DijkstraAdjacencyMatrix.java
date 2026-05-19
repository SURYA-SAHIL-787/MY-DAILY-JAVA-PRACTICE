public class DijkstraAdjacencyMatrix {

    static final int V = 4;

    int minimumDistance(int dist[], boolean visited[]) {

        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minimumDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v] &&
                        graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " Distance " + dist[i]);
        }
    }

    public static void main(String[] args) {

        int graph[][] = {
                {0, 1, 4, 0},
                {1, 0, 2, 6},
                {4, 2, 0, 3},
                {0, 6, 3, 0}
        };

        DijkstraAdjacencyMatrix obj = new DijkstraAdjacencyMatrix();
        obj.dijkstra(graph, 0);
    }
}
