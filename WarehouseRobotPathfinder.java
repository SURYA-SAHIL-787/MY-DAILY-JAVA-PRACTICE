import java.util.*;

class Point {
    int row, col;
    List<String> path;

    public Point(int row, int col, List<String> path) {
        this.row = row;
        this.col = col;
        this.path = new ArrayList<>(path);
    }
}

public class WarehouseRobotPathfinder {
    // 0 = aisle/open path, 1 = obstacle/shelf
    public static List<String> findShortestPath(int[][] grid, int startR, int startC, int targetR, int targetC) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<Point> queue = new LinkedList<>();
        List<String> initialPath = new ArrayList<>();
        initialPath.add("(" + startR + "," + startC + ")");
        
        queue.offer(new Point(startR, startC, initialPath));
        visited[startR][startC] = true;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (curr.row == targetR && curr.col == targetC) {
                return curr.path;
            }

            for (int i = 0; i < 4; i++) {
                int nRow = curr.row + dRow[i];
                int nCol = curr.col + dCol[i];

                if (nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols && grid[nRow][nCol] == 0 && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    List<String> newPath = new ArrayList<>(curr.path);
                    newPath.add("(" + nRow + "," + nCol + ")");
                    queue.offer(new Point(nRow, nCol, newPath));
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    public static void main(String[] args) {
        int[][] warehouseGrid = {
            {0, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 0, 0, 0},
            {0, 1, 1, 0}
        };

        List<String> path = findShortestPath(warehouseGrid, 0, 0, 3, 3);
        System.out.println("Robot Navigation Path: " + path);
    }
}
