public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2},
                {3, 4}
        };

        int[][] b = {
                {5, 6},
                {7, 8}
        };

        int[][] result = new int[2][2];

        System.out.println("Matrix Multiplication:");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
