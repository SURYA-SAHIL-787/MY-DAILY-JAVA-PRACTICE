import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return res;

        int rows = heights.length, cols = heights[0].length;
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        for (int c = 0; c < cols; c++) {
            dfs(0, c, heights, pac, heights[0][c]);
            dfs(rows - 1, c, heights, atl, heights[rows - 1][c]);
        }

        for (int r = 0; r < rows; r++) {
            dfs(r, 0, heights, pac, heights[r][0]);
            dfs(r, cols - 1, heights, atl, heights[r][cols - 1]);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
    }

    private void dfs(int r, int c, int[][] heights, boolean[][] visit, int prevHeight) {
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length || visit[r][c] || heights[r][c] < prevHeight) {
            return;
        }
        visit[r][c] = true;
        dfs(r + 1, c, heights, visit, heights[r][c]);
        dfs(r - 1, c, heights, visit, heights[r][c]);
        dfs(r, c + 1, heights, visit, heights[r][c]);
        dfs(r, c - 1, heights, visit, heights[r][c]);
    }
}
