import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordBreakWays {

    public static long countWays(
        String text,
        Set<String> dictionary
    ) {
        long[] dp = new long[text.length() + 1];
        dp[0] = 1;

        for (int end = 1; end <= text.length(); end++) {
            for (int start = 0; start < end; start++) {
                String word = text.substring(start, end);

                if (dictionary.contains(word)) {
                    dp[end] += dp[start];
                }
            }
        }

        return dp[text.length()];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int dictionarySize = Integer.parseInt(
            scanner.nextLine()
        );

        Set<String> dictionary = new HashSet<>();

        for (int i = 0; i < dictionarySize; i++) {
            dictionary.add(scanner.nextLine());
        }

        System.out.println(countWays(text, dictionary));
        scanner.close();
    }
}
