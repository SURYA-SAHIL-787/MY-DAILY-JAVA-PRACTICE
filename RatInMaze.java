import java.util.*;

public class RatInMaze {

    static void solveMaze(int[][] maze, int row, int col, String path) {
        int n = maze.length;

        if (row < 0 || col < 0 || row >= n || col >= n || maze[row][col] == 0) {
            return;
        }

        if (row == n - 1 && col == n - 1) {
            System.out.println(path);
            return;
        }

        maze[row][col] = 0;

        solveMaze(maze, row + 1, col, path + "D");
        solveMaze(maze, row - 1, col, path + "U");
        solveMaze(maze, row, col + 1, path + "R");
        solveMaze(maze, row, col - 1, path + "L");

        maze[row][col] = 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of maze: ");
        int n = sc.nextInt();

        int[][] maze = new int[n][n];

        System.out.println("Enter maze values:");
        System.out.println("1 means open path, 0 means blocked path");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        System.out.println("All possible paths are:");
        solveMaze(maze, 0, 0, "");
    }
}
