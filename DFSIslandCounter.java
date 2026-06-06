public class DFSIslandCounter {
    public static void dfs(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;

        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static int countIslands(int[][] grid) {
        int count = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    count++;
                    dfs(grid, row, col);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 0},
            {0, 1, 0, 1},
            {0, 0, 0, 1},
            {1, 0, 0, 0}
        };

        System.out.println("Number of islands: " + countIslands(grid));
    }
}
