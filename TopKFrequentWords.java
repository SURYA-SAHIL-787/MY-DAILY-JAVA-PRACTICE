import java.util.*;

public class TopKFrequentWords {

    public static List<String> findTopKFrequentWords(String[] words, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((word1, word2) -> {
            int frequency1 = frequencyMap.get(word1);
            int frequency2 = frequencyMap.get(word2);

            if (frequency1 != frequency2) {
                return frequency2 - frequency1;
            }

            return word1.compareTo(word2);
        });

        maxHeap.addAll(frequencyMap.keySet());

        List<String> result = new ArrayList<>();

        while (k > 0 && !maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
            k--;
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {
            "java", "heap", "trie", "java",
            "string", "heap", "java", "trie"
        };

        int k = 3;

        List<String> result = findTopKFrequentWords(words, k);

        System.out.println("Top " + k + " frequent words:");

        for (String word : result) {
            System.out.println(word);
        }
    }
}
