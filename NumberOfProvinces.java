class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visit = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(i, isConnected, visit);
                provinces++;
            }
        }
        return provinces;
    }

    private void dfs(int node, int[][] isConnected, boolean[] visit) {
        visit[node] = true;
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[node][neighbor] == 1 && !visit[neighbor]) {
                dfs(neighbor, isConnected, visit);
            }
        }
    }
}
