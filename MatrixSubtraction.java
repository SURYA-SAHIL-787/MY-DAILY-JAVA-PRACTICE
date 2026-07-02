public class MatrixSubtraction {
    public static void main(String[] args) {
        int[][] a = {
                {10, 8},
                {6, 4}
        };

        int[][] b = {
                {2, 3},
                {1, 2}
        };

        int[][] result = new int[2][2];

        System.out.println("Matrix Subtraction:");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = a[i][j] - b[i][j];
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
