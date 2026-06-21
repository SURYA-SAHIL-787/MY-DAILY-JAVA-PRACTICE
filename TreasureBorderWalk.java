import java.util.*;

public class TreasureBorderWalk {
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

        int sum = 0;

        System.out.print("Border Walk: ");

        for (int j = 0; j < cols; j++) {
            System.out.print(matrix[0][j] + " ");
            sum += matrix[0][j];
        }

        for (int i = 1; i < rows; i++) {
            System.out.print(matrix[i][cols - 1] + " ");
            sum += matrix[i][cols - 1];
        }

        if (rows > 1) {
            for (int j = cols - 2; j >= 0; j--) {
                System.out.print(matrix[rows - 1][j] + " ");
                sum += matrix[rows - 1][j];
            }
        }

        if (cols > 1) {
            for (int i = rows - 2; i >= 1; i--) {
                System.out.print(matrix[i][0] + " ");
                sum += matrix[i][0];
            }
        }

        System.out.println();
        System.out.println("Border Sum: " + sum);

        sc.close();
    }
}
