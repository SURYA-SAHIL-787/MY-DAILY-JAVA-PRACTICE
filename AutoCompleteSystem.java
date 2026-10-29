import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
    List<String> suggestions = new ArrayList<>(); // Cache top suggestions for fast retrieval
}

class SearchAutoComplete {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
            // Maintain top suggestions cache (e.g., limit to 3)
            if (!current.suggestions.contains(word)) {
                if (current.suggestions.size() < 3) {
                    current.suggestions.add(word);
                }
            }
        }
        current.isEndOfWord = true;
    }

    public List<String> getSuggestions(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return Collections.emptyList(); // Prefix not found
            }
            current = current.children.get(c);
        }
        return current.suggestions;
    }
}

public class AutoCompleteSystem {
    public static void main(String[] args) {
        SearchAutoComplete engine = new SearchAutoComplete();
        engine.insert("java tutorial");
        engine.insert("javascript");
        engine.insert("java compiler");
        engine.insert("javafx");

        System.out.println("Suggestions for 'jav': " + engine.getSuggestions("jav"));
    }
}
