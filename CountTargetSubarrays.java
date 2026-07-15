import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountTargetSubarrays {

    public static long countSubarrays(int[] numbers, long target) {
        Map<Long, Integer> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0L, 1);

        long prefixSum = 0;
        long count = 0;

        for (int number : numbers) {
            prefixSum += number;

            count += prefixFrequency.getOrDefault(
                prefixSum - target,
                0
            );

            prefixFrequency.put(
                prefixSum,
                prefixFrequency.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        long target = scanner.nextLong();

        System.out.println(countSubarrays(numbers, target));
        scanner.close();
    }
}
