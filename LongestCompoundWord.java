import java.util.*;

public class LongestCompoundWord {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode current = root;

            for (char character : word.toCharArray()) {
                int index = character - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.isEndOfWord = true;
        }

        public boolean canFormCompoundWord(String word) {
            Boolean[] memo = new Boolean[word.length() + 1];
            return canForm(word, 0, 0, memo);
        }

        private boolean canForm(
            String word,
            int start,
            int numberOfParts,
            Boolean[] memo
        ) {
            if (start == word.length()) {
                return numberOfParts >= 2;
            }

            if (numberOfParts > 0 && memo[start] != null) {
                return memo[start];
            }

            TrieNode current = root;

            for (int index = start; index < word.length(); index++) {
                int childIndex = word.charAt(index) - 'a';

                if (current.children[childIndex] == null) {
                    break;
                }

                current = current.children[childIndex];

                if (current.isEndOfWord) {
                    boolean possible = canForm(
                        word,
                        index + 1,
                        numberOfParts + 1,
                        memo
                    );

                    if (possible) {
                        if (numberOfParts > 0) {
                            memo[start] = true;
                        }

                        return true;
                    }
                }
            }

            if (numberOfParts > 0) {
                memo[start] = false;
            }

            return false;
        }
    }

    public static String findLongestCompoundWord(String[] words) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        Arrays.sort(words, (word1, word2) -> {
            if (word1.length() != word2.length()) {
                return word2.length() - word1.length();
            }

            return word1.compareTo(word2);
        });

        for (String word : words) {
            if (trie.canFormCompoundWord(word)) {
                return word;
            }
        }

        return "No compound word found";
    }

    public static void main(String[] args) {
        String[] words = {
            "basket",
            "ball",
            "basketball",
            "foot",
            "football",
            "player",
            "footballplayer",
            "play",
            "ground"
        };

        String result = findLongestCompoundWord(words);

        System.out.println("Longest compound word: " + result);
    }
}
