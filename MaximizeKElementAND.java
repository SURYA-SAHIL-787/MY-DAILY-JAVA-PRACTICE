import java.util.Arrays;

public class MaximizeKElementAND {

    private static long calculateCost(long num, long mask) {
        long value = num;

        for (int bit = 30; bit >= 0; bit--) {
            long power = 1L << bit;

            if ((mask & power) != 0 && (value & power) == 0) {
                value += power - (value & (power - 1));
            }
        }

        return value - num;
    }

    public static long maximizeAND(int[] nums, int k, long budget) {
        long answer = 0;

        for (int bit = 30; bit >= 0; bit--) {
            long candidate = answer | (1L << bit);
            long[] costs = new long[nums.length];

            for (int i = 0; i < nums.length; i++) {
                costs[i] = calculateCost(nums[i], candidate);
            }

            Arrays.sort(costs);

            long totalCost = 0;

            for (int i = 0; i < k; i++) {
                totalCost += costs[i];

                if (totalCost > budget) {
                    break;
                }
            }

            if (totalCost <= budget) {
                answer = candidate;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 1, 7, 2};

        int k = 3;
        long budget = 3;

        System.out.println(maximizeAND(nums, k, budget));
    }
}
