import java.util.HashMap;
import java.util.Map;

public class DistinctSubsequenceCounter {

    private static final long MOD = 1_000_000_007L;

    public static long countDistinctSubsequences(String text) {
        int n = text.length();

        long[] dp = new long[n + 1];
        dp[0] = 1;

        Map<Character, Integer> lastPosition = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            char currentCharacter = text.charAt(i - 1);

            dp[i] = (2 * dp[i - 1]) % MOD;

            if (lastPosition.containsKey(currentCharacter)) {
                int previousPosition =
                    lastPosition.get(currentCharacter);

                dp[i] = (
                    dp[i] - dp[previousPosition - 1] + MOD
                ) % MOD;
            }

            lastPosition.put(currentCharacter, i);
        }

        return (dp[n] - 1 + MOD) % MOD;
    }

    public static void main(String[] args) {
        String text = "aba";

        System.out.println(
            "Distinct non-empty subsequences: "
            + countDistinctSubsequences(text)
        );
    }
}
