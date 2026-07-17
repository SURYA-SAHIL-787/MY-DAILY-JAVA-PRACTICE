import java.util.HashMap;
import java.util.Map;

public class FixedDifferenceSubsequence {

    public static int longestSubsequence(
        int[] nums,
        int difference
    ) {
        Map<Integer, Integer> dp = new HashMap<>();
        int answer = 0;

        for (int number : nums) {
            int previousNumber = number - difference;

            int currentLength =
                dp.getOrDefault(previousNumber, 0) + 1;

            dp.put(
                number,
                Math.max(
                    dp.getOrDefault(number, 0),
                    currentLength
                )
            );

            answer = Math.max(answer, dp.get(number));
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 7, 8, 5, 3, 4, 2, 1};
        int difference = -2;

        System.out.println(
            "Longest subsequence length: "
            + longestSubsequence(nums, difference)
        );
    }
}
