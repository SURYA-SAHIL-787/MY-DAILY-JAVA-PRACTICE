import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestUniqueSubstring {

    public static int findLongestLength(String text) {
        Map<Character, Integer> lastIndex = new HashMap<>();

        int left = 0;
        int maximumLength = 0;

        for (int right = 0; right < text.length(); right++) {
            char current = text.charAt(right);

            if (lastIndex.containsKey(current)) {
                left = Math.max(left, lastIndex.get(current) + 1);
            }

            lastIndex.put(current, right);
            maximumLength = Math.max(maximumLength, right - left + 1);
        }

        return maximumLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        System.out.println(findLongestLength(text));
        scanner.close();
    }
}
