import java.util.*;

public class TrieAutocomplete {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
        int frequency;
    }

    static class WordFrequency {
        String word;
        int frequency;

        WordFrequency(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word, int frequency) {
            TrieNode current = root;

            for (char character : word.toCharArray()) {
                current.children.putIfAbsent(character, new TrieNode());
                current = current.children.get(character);
            }

            current.isEndOfWord = true;
            current.frequency = frequency;
        }

        public List<String> getSuggestions(String prefix, int k) {
            TrieNode current = root;

            for (char character : prefix.toCharArray()) {
                if (!current.children.containsKey(character)) {
                    return new ArrayList<>();
                }

                current = current.children.get(character);
            }

            List<WordFrequency> matchingWords = new ArrayList<>();
            collectWords(current, prefix, matchingWords);

            PriorityQueue<WordFrequency> maxHeap =
                new PriorityQueue<>((first, second) -> {
                    if (first.frequency != second.frequency) {
                        return second.frequency - first.frequency;
                    }

                    return first.word.compareTo(second.word);
                });

            maxHeap.addAll(matchingWords);

            List<String> suggestions = new ArrayList<>();

            while (k > 0 && !maxHeap.isEmpty()) {
                suggestions.add(maxHeap.poll().word);
                k--;
            }

            return suggestions;
        }

        private void collectWords(
            TrieNode node,
            String currentWord,
            List<WordFrequency> words
        ) {
            if (node.isEndOfWord) {
                words.add(new WordFrequency(currentWord, node.frequency));
            }

            for (Map.Entry<Character, TrieNode> entry
                    : node.children.entrySet()) {
                collectWords(
                    entry.getValue(),
                    currentWord + entry.getKey(),
                    words
                );
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple", 10);
        trie.insert("application", 7);
        trie.insert("apply", 8);
        trie.insert("app", 15);
        trie.insert("aptitude", 5);
        trie.insert("banana", 12);

        String prefix = "app";
        int k = 3;

        List<String> suggestions = trie.getSuggestions(prefix, k);

        System.out.println("Top suggestions for \"" + prefix + "\":");

        for (String word : suggestions) {
            System.out.println(word);
        }
    }
}
