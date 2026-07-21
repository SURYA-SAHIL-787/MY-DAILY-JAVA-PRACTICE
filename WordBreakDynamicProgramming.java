import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordBreakDynamicProgramming {

    static boolean canSegmentString(
            String text,
            Set<String> dictionary) {

        boolean[] dp = new boolean[text.length() + 1];
        dp[0] = true;

        for (int end = 1; end <= text.length(); end++) {
            for (int start = 0; start < end; start++) {
                String currentWord =
                        text.substring(start, end);

                if (dp[start]
                        && dictionary.contains(currentWord)) {
                    dp[end] = true;
                    break;
                }
            }
        }

        return dp[text.length()];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.next();
        int dictionarySize = scanner.nextInt();

        Set<String> dictionary = new HashSet<>();

        for (int i = 0; i < dictionarySize; i++) {
            dictionary.add(scanner.next());
        }

        if (canSegmentString(text, dictionary)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }
}
