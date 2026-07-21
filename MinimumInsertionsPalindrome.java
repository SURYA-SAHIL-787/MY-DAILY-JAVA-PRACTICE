import java.util.Scanner;

public class MinimumInsertionsPalindrome {

    static int findMinimumInsertions(String text) {
        int length = text.length();

        if (length <= 1) {
            return 0;
        }

        int[][] dp = new int[length][length];

        for (int substringLength = 2;
             substringLength <= length;
             substringLength++) {

            for (int start = 0;
                 start <= length - substringLength;
                 start++) {

                int end = start + substringLength - 1;

                if (text.charAt(start) == text.charAt(end)) {
                    dp[start][end] =
                            substringLength == 2
                                    ? 0
                                    : dp[start + 1][end - 1];
                } else {
                    dp[start][end] = 1 + Math.min(
                            dp[start + 1][end],
                            dp[start][end - 1]
                    );
                }
            }
        }

        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        System.out.println(findMinimumInsertions(text));
        scanner.close();
    }
}
