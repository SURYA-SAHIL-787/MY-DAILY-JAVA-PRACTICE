import java.util.*;

public class MatrixPeakCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int peakCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPeak(matrix, i, j)) {
                    peakCount++;
                }
            }
        }

        System.out.println("Peak Count: " + peakCount);

        sc.close();
    }

    static boolean isPeak(int[][] matrix, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int current = matrix[row][col];

        if (row - 1 >= 0 && matrix[row - 1][col] >= current) {
            return false;
        }

        if (row + 1 < rows && matrix[row + 1][col] >= current) {
            return false;
        }

        if (col - 1 >= 0 && matrix[row][col - 1] >= current) {
            return false;
        }

        if (col + 1 < cols && matrix[row][col + 1] >= current) {
            return false;
        }

        return true;
    }
}
