import java.util.Scanner;

public class MaximumNonAdjacentSum {

    public static long findMaximumSum(int[] numbers) {
        long previousTwo = 0;
        long previousOne = 0;

        for (int number : numbers) {
            long current = Math.max(previousOne, previousTwo + number);
            previousTwo = previousOne;
            previousOne = current;
        }

        return previousOne;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(findMaximumSum(numbers));
        scanner.close();
    }
}
