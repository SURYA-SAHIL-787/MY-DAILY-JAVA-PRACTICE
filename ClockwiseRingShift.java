import java.util.*;

public class ClockwiseRingShift {
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

        if (rows == 1 && cols == 1) {
            printMatrix(matrix);
            sc.close();
            return;
        }

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j];
            }
        }

        if (rows == 1) {
            int last = matrix[0][cols - 1];

            for (int j = cols - 1; j > 0; j--) {
                result[0][j] = matrix[0][j - 1];
            }

            result[0][0] = last;
        } else if (cols == 1) {
            int last = matrix[rows - 1][0];

            for (int i = rows - 1; i > 0; i--) {
                result[i][0] = matrix[i - 1][0];
            }

            result[0][0] = last;
        } else {
            for (int j = 1; j < cols; j++) {
                result[0][j] = matrix[0][j - 1];
            }

            for (int i = 1; i < rows; i++) {
                result[i][cols - 1] = matrix[i - 1][cols - 1];
            }

            for (int j = cols - 2; j >= 0; j--) {
                result[rows - 1][j] = matrix[rows - 1][j + 1];
            }

            for (int i = rows - 2; i >= 0; i--) {
                result[i][0] = matrix[i + 1][0];
            }
        }

        printMatrix(result);
        sc.close();
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
