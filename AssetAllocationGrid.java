import java.util.*;

// 1. AssetCell Class
class AssetCell {
    private int row;
    private int col;
    private int returnValue;
    private boolean isHazard;

    public AssetCell(int row, int col, int returnValue) {
        this.row = row;
        this.col = col;
        this.returnValue = returnValue;
        this.isHazard = returnValue < 0;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getReturnValue() { return returnValue; }
    public boolean isHazard() { return isHazard; }
}

// 2. PortfolioGrid Class
class PortfolioGrid {
    private AssetCell[][] grid;
    private int rows;
    private int cols;

    public PortfolioGrid(int[][] rawValues) {
        this.rows = rawValues.length;
        this.cols = rawValues[0].length;
        this.grid = new AssetCell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new AssetCell(i, j, rawValues[i][j]);
            }
        }
    }

    public AssetCell getCell(int r, int c) { return grid[r][c]; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
}

// Optimization Result Container
class PathResult {
    public int maxReturn;
    public List<String> pathCoordinates;

    public PathResult(int maxReturn, List<String> pathCoordinates) {
        this.maxReturn = maxReturn;
        this.pathCoordinates = pathCoordinates;
    }
}

// 3. AssetOptimizer Class
class AssetOptimizer {
    public PathResult findOptimalPath(PortfolioGrid grid, int kMax) {
        int rows = grid.getRows();
        int cols = grid.getCols();
        
        // DP table: dp[r][c][k] stores max return at (r,c) with k hazards bypassed
        int[][][] dp = new int[rows][cols][kMax + 1];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        int maxRet = solve(grid, rows - 1, cols - 1, kMax, dp);
        List<String> path = reconstructPath(grid, rows - 1, cols - 1, kMax, dp);
        return new PathResult(maxRet, path);
    }

    private int solve(PortfolioGrid grid, int r, int c, int k, int[][][] dp) {
        if (r < 0 || c < 0) return Integer.MIN_VALUE;
        AssetCell cell = grid.getCell(r, c);
        
        int hazardsUsed = cell.isHazard() ? 1 : 0;
        if (k < hazardsUsed) return Integer.MIN_VALUE;

        if (r == 0 && c == 0) {
            return cell.isHazard() ? cell.getReturnValue() : cell.getReturnValue();
        }

        if (dp[r][c][k] != -1) return dp[r][c][k];

        int remainingK = k - hazardsUsed;
        int fromTop = solve(grid, r - 1, c, remainingK, dp);
        int fromLeft = solve(grid, r, c - 1, remainingK, dp);

        int bestPrev = Math.max(fromTop, fromLeft);
        if (bestPrev == Integer.MIN_VALUE) {
            return dp[r][c][k] = Integer.MIN_VALUE;
        }

        return dp[r][c][k] = bestPrev + cell.getReturnValue();
    }

    private List<String> reconstructPath(PortfolioGrid grid, int r, int c, int k, int[][][] dp) {
        List<String> path = new ArrayList<>();
        path.add("(" + r + "," + c + ")");

        while (r > 0 || c > 0) {
            AssetCell cell = grid.getCell(r, c);
            int hazardsUsed = cell.isHazard() ? 1 : 0;
            int nextK = k - hazardsUsed;

            int valTop = (r > 0) ? dp[r - 1][c][nextK] : Integer.MIN_VALUE;
            int valLeft = (c > 0) ? dp[r][c - 1][nextK] : Integer.MIN_VALUE;

            if (valTop >= valLeft && r > 0) {
                r--;
            } else {
                c--;
            }
            k = nextK;
            path.add(0, "(" + r + "," + c + ")");
        }
        return path;
    }
}

// Execution Runner
public class AssetAllocationGrid {
    public static void main(String[] args) {
        int[][] rawValues = {
            {5, 2, -3},
            {1, -5, 2},
            {3, 4, 10}
        };

        PortfolioGrid portfolioGrid = new PortfolioGrid(rawValues);
        AssetOptimizer optimizer = new AssetOptimizer();
        PathResult result = optimizer.findOptimalPath(portfolioGrid, 2);

        System.out.println("Maximum Cumulative Return: " + result.maxReturn);
        System.out.println("Optimal Asset Path: " + result.pathCoordinates);
    }
}
