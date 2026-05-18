import java.util.*;

public class NumberOfIslandsBFS {
    static int rows;
    static int cols;

    static void bfs(int row, int col, char[][] grid, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();

        visited[row][col] = true;
        queue.add(new int[]{row, col});

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = current[0] + dRow[i];
                int newCol = current[1] + dCol[i];

                if (
                    newRow >= 0 &&
                    newRow < rows &&
                    newCol >= 0 &&
                    newCol < cols &&
                    grid[newRow][newCol] == '1' &&
                    !visited[newRow][newCol]
                ) {
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
    }

    static int countIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    count++;
                    bfs(row, col, grid, visited);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '0', '0', '1'},
            {'0', '0', '1', '1'},
            {'0', '0', '0', '0'}
        };

        System.out.println(countIslands(grid));
    }
}
