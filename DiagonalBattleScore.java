import java.util.*;

public class DiagonalBattleScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int primarySum = 0;
        int secondarySum = 0;

        for (int i = 0; i < n; i++) {
            primarySum += matrix[i][i];
            secondarySum += matrix[i][n - 1 - i];
        }

        int totalScore = primarySum + secondarySum;

        if (n % 2 == 1) {
            totalScore -= matrix[n / 2][n / 2];
        }

        System.out.println("Primary Diagonal Sum: " + primarySum);
        System.out.println("Secondary Diagonal Sum: " + secondarySum);
        System.out.println("Total Diagonal Score: " + totalScore);

        if (primarySum > secondarySum) {
            System.out.println("Result: Primary Diagonal Wins");
        } else if (secondarySum > primarySum) {
            System.out.println("Result: Secondary Diagonal Wins");
        } else {
            System.out.println("Result: Draw");
        }

        sc.close();
    }
}
