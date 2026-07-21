import java.util.Scanner;

public class StringInterleavingDP {

    static boolean isInterleaving(
            String first,
            String second,
            String target) {

        if (first.length() + second.length()
                != target.length()) {
            return false;
        }

        boolean[][] dp =
                new boolean[first.length() + 1]
                        [second.length() + 1];

        dp[0][0] = true;

        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int targetIndex = i + j - 1;

                if (i > 0
                        && first.charAt(i - 1)
                        == target.charAt(targetIndex)) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }

                if (j > 0
                        && second.charAt(j - 1)
                        == target.charAt(targetIndex)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }

        return dp[first.length()][second.length()];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();
        String target = scanner.nextLine();

        if (isInterleaving(first, second, target)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }
}
