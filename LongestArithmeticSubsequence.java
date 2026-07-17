import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequence {

    public static int longestArithSeqLength(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return n;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int answer = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long differenceLong = (long) nums[i] - nums[j];

                if (differenceLong < Integer.MIN_VALUE ||
                    differenceLong > Integer.MAX_VALUE) {
                    continue;
                }

                int difference = (int) differenceLong;
                int previousLength = dp[j].getOrDefault(difference, 1);
                int currentLength = previousLength + 1;

                dp[i].put(
                    difference,
                    Math.max(dp[i].getOrDefault(difference, 0), currentLength)
                );

                answer = Math.max(answer, currentLength);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 12};

        System.out.println(
            "Longest arithmetic subsequence length: "
            + longestArithSeqLength(nums)
        );
    }
}
