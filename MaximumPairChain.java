import java.util.Arrays;

public class MaximumPairChain {

    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(
            pairs,
            (first, second) -> Integer.compare(
                first[0],
                second[0]
            )
        );

        int n = pairs.length;
        int[] dp = new int[n];
        int answer = 0;

        Arrays.fill(dp, 1);

        for (int current = 0; current < n; current++) {
            for (int previous = 0;
                 previous < current;
                 previous++) {

                if (pairs[previous][1] < pairs[current][0]) {
                    dp[current] = Math.max(
                        dp[current],
                        dp[previous] + 1
                    );
                }
            }

            answer = Math.max(answer, dp[current]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] pairs = {
            {1, 2},
            {2, 3},
            {3, 4},
            {5, 6}
        };

        System.out.println(
            "Maximum pair-chain length: "
            + findLongestChain(pairs)
        );
    }
}
