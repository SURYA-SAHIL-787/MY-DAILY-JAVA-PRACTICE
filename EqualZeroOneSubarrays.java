import java.util.HashMap;
import java.util.Map;

public class EqualZeroOneSubarrays {

    public static long countEqualZeroOneSubarrays(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();

        frequency.put(0, 1);

        int prefixSum = 0;
        long count = 0;

        for (int number : nums) {
            if (number == 0) {
                prefixSum--;
            } else {
                prefixSum++;
            }

            count += frequency.getOrDefault(prefixSum, 0);

            frequency.put(
                prefixSum,
                frequency.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 1, 0};

        System.out.println(
            "Number of balanced subarrays: "
            + countEqualZeroOneSubarrays(nums)
        );
    }
}
