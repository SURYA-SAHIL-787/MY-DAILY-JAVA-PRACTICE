import java.util.*;

public class KthLargestString {

    public static String findKthLargestString(String[] words, int k) {
        if (words == null || k <= 0 || k > words.length) {
            throw new IllegalArgumentException("Invalid value of k");
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((word1, word2) -> {
            if (word1.length() != word2.length()) {
                return word1.length() - word2.length();
            }

            return word2.compareTo(word1);
        });

        for (String word : words) {
            minHeap.offer(word);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        String[] words = {
            "java",
            "heap",
            "algorithm",
            "trie",
            "programming",
            "string",
            "computer"
        };

        int k = 3;

        String answer = findKthLargestString(words, k);

        System.out.println(
            "String with the " + k + "rd largest length: " + answer
        );

        System.out.println("Length: " + answer.length());
    }
}
