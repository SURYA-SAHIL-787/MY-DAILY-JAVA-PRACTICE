import java.util.HashMap;
import java.util.Map;

public class FibonacciLikeSubsequence {

    public static int lenLongestFibSubseq(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.put(nums[i], i);
        }

        int[][] dp = new int[n][n];
        int answer = 0;

        for (int second = 0; second < n; second++) {
            for (int third = second + 1; third < n; third++) {
                int requiredValue = nums[third] - nums[second];

                Integer first = indexMap.get(requiredValue);

                if (first != null && first < second) {
                    dp[second][third] =
                        Math.max(3, dp[first][second] + 1);

                    answer = Math.max(answer, dp[second][third]);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println(
            "Longest Fibonacci-like subsequence: "
            + lenLongestFibSubseq(nums)
        );
    }
}
