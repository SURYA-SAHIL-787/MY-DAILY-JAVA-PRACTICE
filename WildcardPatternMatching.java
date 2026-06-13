public class WildcardPatternMatching {

    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char patternChar = p.charAt(j - 1);
                char stringChar = s.charAt(i - 1);

                if (patternChar == stringChar || patternChar == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (patternChar == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }
}
