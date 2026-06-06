import java.util.*;

public class NearestPoliceStation {
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int[][] nearestPoliceStation(int[][] city) {
        int rows = city.length;
        int cols = city[0].length;

        int[][] distance = new int[rows][cols];
        Queue<Cell> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], -1);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (city[row][col] == 1) {
                    queue.add(new Cell(row, col));
                    distance[row][col] = 0;
                }
            }
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = current.row + dRow[i];
                int newCol = current.col + dCol[i];

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    city[newRow][newCol] != -1 &&
                    distance[newRow][newCol] == -1) {

                    distance[newRow][newCol] = distance[current.row][current.col] + 1;
                    queue.add(new Cell(newRow, newCol));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int[][] city = {
            {0, 0, 1},
            {0, -1, 0},
            {0, 0, 0}
        };

        int[][] result = nearestPoliceStation(city);

        System.out.println("Minimum distance to nearest police station:");

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
